package volio.tech.sharefile.business.interactors

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import volio.tech.sharefile.business.data.DataState
import volio.tech.sharefile.business.data.cache.abstraction.FileCacheDataSource
import volio.tech.sharefile.business.data.cache.abstraction.TransferFileCacheDataSource
import volio.tech.sharefile.business.domain.Dummy
import volio.tech.sharefile.business.domain.FileModel
import volio.tech.sharefile.business.domain.TransferFile
import volio.tech.sharefile.business.domain.TransferFile.Companion.IS_PENDING
import volio.tech.sharefile.business.domain.TransferFile.Companion.WAITING_TO_SEND
import javax.inject.Inject

class CancelFile
@Inject
constructor(
    private val fileCacheDataSource: FileCacheDataSource,
    private val transferFileCacheDataSource: TransferFileCacheDataSource
) {
    suspend fun cancelFile(idFile: Int): Flow<DataState<FileModel>> =
        flow {
            emit(DataState.Loading())

            val fileModel = fileCacheDataSource.getFileById(idFile)
            fileModel?.let {
                it.isStatusTransfer = FileModel.IS_CANCEL
                fileCacheDataSource.updateFile(it)
            }
            emit(DataState.Success(fileModel))
        }
}
