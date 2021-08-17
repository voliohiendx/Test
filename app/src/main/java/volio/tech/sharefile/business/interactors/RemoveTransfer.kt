package volio.tech.sharefile.business.interactors

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import volio.tech.sharefile.business.data.DataState
import volio.tech.sharefile.business.data.cache.abstraction.FileCacheDataSource
import volio.tech.sharefile.business.data.cache.abstraction.TransferFileCacheDataSource
import volio.tech.sharefile.business.domain.FileModel
import volio.tech.sharefile.business.domain.TransferFile
import javax.inject.Inject

class RemoveTransfer
@Inject
constructor(
    private val fileCacheDataSource: FileCacheDataSource,
    private val transferFileCacheDataSource: TransferFileCacheDataSource
) {
    suspend fun removeTransfer(idTransfer: Int): Flow<DataState<TransferFile>> = flow {
        emit(DataState.Loading())
        transferFileCacheDataSource.getTransferFileById(idTransfer)?.let {
            val files = fileCacheDataSource.getFileByIdTransfer(idTransfer.toLong())
            files.forEach {
                fileCacheDataSource.removeFile(it)
            }
            transferFileCacheDataSource.removeTransferFile(it)
            emit(DataState.Success(it))
        }
    }
}
