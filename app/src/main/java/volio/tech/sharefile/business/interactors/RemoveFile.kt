package volio.tech.sharefile.business.interactors

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import volio.tech.sharefile.business.data.DataState
import volio.tech.sharefile.business.data.cache.abstraction.FileCacheDataSource
import volio.tech.sharefile.business.data.cache.abstraction.TransferFileCacheDataSource
import volio.tech.sharefile.business.domain.FileModel
import javax.inject.Inject

class RemoveFile
@Inject
constructor(
    private val fileCacheDataSource: FileCacheDataSource,
    private val transferFileCacheDataSource: TransferFileCacheDataSource
) {
    suspend fun removeFile(idFile: Int): Flow<DataState<FileModel>> =
        flow {
            emit(DataState.Loading())

            val fileModel = fileCacheDataSource.getFileById(idFile)
            fileModel?.let { fileModel ->
                val transferFile =
                    transferFileCacheDataSource.getTransferFileById(fileModel.idTransfer.toInt())
                transferFile?.let { transferFile ->
                    if (transferFile.amountFile == 1) {
                        transferFileCacheDataSource.removeTransferFile(transferFile)
                    } else {
                        transferFile.sizeTransfer = transferFile.sizeTransfer - fileModel.size
                        transferFile.amountFile = transferFile.amountFile - 1
                        transferFileCacheDataSource.updateTransferFile(transferFile)
                    }
                    fileCacheDataSource.removeFile(fileModel)
                }
            }
            emit(DataState.Success(fileModel))
        }
}
