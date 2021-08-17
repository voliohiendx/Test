package volio.tech.sharefile.business.interactors

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import volio.tech.sharefile.business.data.DataState
import volio.tech.sharefile.business.data.cache.abstraction.FileCacheDataSource
import volio.tech.sharefile.business.data.cache.abstraction.TransferFileCacheDataSource
import volio.tech.sharefile.business.domain.FileModel
import volio.tech.sharefile.business.domain.TransferFile
import javax.inject.Inject

class UpdatePort
@Inject
constructor(
    private val fileCacheDataSource: FileCacheDataSource,
    private val transferFileCacheDataSource: TransferFileCacheDataSource
) {

    suspend fun updatePortPending(tokenPorts: String): Flow<DataState<List<TransferFile>>> =
        flow {
            emit(DataState.Loading())

            val transferFiles = transferFileCacheDataSource.getTransferByTokenPort(tokenPorts)

            if (!transferFiles.isNullOrEmpty()) {
                transferFiles.forEach {
                    transferFileCacheDataSource.getTransferFileById(it.idTransfer.toInt())
                        ?.let { transferFiles ->
                            if (transferFiles.isStatus == TransferFile.WAITING_TO_SEND
                                || transferFiles.isStatus == TransferFile.IS_SENDING
                            ) {
                                transferFiles.isStatus = TransferFile.IS_PENDING
                                val files =
                                    fileCacheDataSource.getFileByIdTransfer(transferFiles.idTransfer)

                                files.forEach { transferFiles ->
                                    if (transferFiles.isStatusTransfer == FileModel.WAITING_TO_SEND
                                        || transferFiles.isStatusTransfer == FileModel.IS_SENDING
                                    ) {
                                        transferFiles.isStatusTransfer = FileModel.IS_PENDING
                                        fileCacheDataSource.updateFile(transferFiles)
                                    }
                                }
                                transferFileCacheDataSource.updateTransferFile(transferFiles)
                            }
                        }
                }
                emit(DataState.Success(transferFiles))
            }
        }

    suspend fun updatePortCancel(tokenPorts: String): Flow<DataState<List<TransferFile>>> =
        flow {
            emit(DataState.Loading())

            val transferFiles = transferFileCacheDataSource.getTransferByTokenPort(tokenPorts)

            if (!transferFiles.isNullOrEmpty()) {
                transferFiles.forEach {
                    transferFileCacheDataSource.getTransferFileById(it.idTransfer.toInt())
                        ?.let { transferFiles ->
                            if (transferFiles.isStatus == TransferFile.WAITING_TO_SEND
                                || transferFiles.isStatus == TransferFile.IS_SENDING
                            ) {
                                val files =
                                    fileCacheDataSource.getFileByIdTransfer(transferFiles.idTransfer)
                                files.forEach { fileModel ->
                                    fileModel.isStatusTransfer = FileModel.IS_CANCEL
                                    fileCacheDataSource.updateFile(fileModel)
                                }
                                transferFiles.isStatus = TransferFile.IS_SUCCESS
                                transferFileCacheDataSource.updateTransferFile(transferFiles)
                            }
                        }
                }
                emit(DataState.Success(transferFiles))
            }
        }
}
