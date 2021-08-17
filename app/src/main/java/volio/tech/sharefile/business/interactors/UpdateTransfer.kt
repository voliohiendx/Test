package volio.tech.sharefile.business.interactors

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import volio.tech.sharefile.business.data.DataState
import volio.tech.sharefile.business.data.cache.abstraction.FileCacheDataSource
import volio.tech.sharefile.business.data.cache.abstraction.TransferFileCacheDataSource
import volio.tech.sharefile.business.domain.TransferFile
import javax.inject.Inject

class UpdateTransfer
@Inject
constructor(
    private val fileCacheDataSource: FileCacheDataSource,
    private val transferFileCacheDataSource: TransferFileCacheDataSource
) {
    suspend fun updateTransferSuccess(idTransferFile: Int): Flow<DataState<TransferFile>> =
        flow {
            emit(DataState.Loading())

            val transferFiles = transferFileCacheDataSource.getTransferFileById(idTransferFile)
            transferFiles?.let {
                it.isStatus= TransferFile.IS_SUCCESS
                transferFileCacheDataSource.updateTransferFile(it)
                emit(DataState.Success(it))
            }
        }
}
