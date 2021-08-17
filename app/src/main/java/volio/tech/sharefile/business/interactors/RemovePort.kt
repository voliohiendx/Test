package volio.tech.sharefile.business.interactors

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import volio.tech.sharefile.business.data.DataState
import volio.tech.sharefile.business.data.cache.abstraction.FileCacheDataSource
import volio.tech.sharefile.business.data.cache.abstraction.TransferFileCacheDataSource
import volio.tech.sharefile.business.domain.FileModel
import volio.tech.sharefile.business.domain.TransferFile
import javax.inject.Inject

class RemovePort
@Inject
constructor(
    private val fileCacheDataSource: FileCacheDataSource,
    private val transferFileCacheDataSource: TransferFileCacheDataSource
) {
    suspend fun removePort(tokenPorts: String): Flow<DataState<List<TransferFile>>> = flow {
        emit(DataState.Loading())
        val transferFiles = transferFileCacheDataSource.getTransferByTokenPort(tokenPorts)

        if (!transferFiles.isNullOrEmpty()) {
            transferFiles.forEach {
                transferFileCacheDataSource.getTransferFileById(it.idTransfer.toInt())?.let {
                    val files = fileCacheDataSource.getFileByIdTransfer(it.idTransfer)
                    files.forEach {
                        fileCacheDataSource.removeFile(it)
                    }
                    transferFileCacheDataSource.removeTransferFile(it)
                }
            }
            emit(DataState.Success(transferFiles))
        }
    }
}
