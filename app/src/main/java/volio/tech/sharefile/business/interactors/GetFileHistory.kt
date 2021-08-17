package volio.tech.sharefile.business.interactors

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import volio.tech.sharefile.business.data.DataState
import volio.tech.sharefile.business.data.cache.abstraction.FileCacheDataSource
import volio.tech.sharefile.business.data.cache.abstraction.TransferFileCacheDataSource
import volio.tech.sharefile.business.domain.FileModel
import volio.tech.sharefile.business.domain.HistoryModel
import volio.tech.sharefile.business.domain.TransferFile
import volio.tech.sharefile.business.domain.TransferFile.Companion.IS_SUCCESS
import javax.inject.Inject

class GetFileHistory
@Inject
constructor(
    private val fileCacheDataSource: FileCacheDataSource,
    private val transferFileCacheDataSource: TransferFileCacheDataSource
) {
    suspend fun getFileHistory(typeTransfer: Int): Flow<DataState<List<HistoryModel>>> = flow {
        emit(DataState.Loading())
        val historys = mutableListOf<HistoryModel>()
        val transferFile = transferFileCacheDataSource.getTransferByType(typeTransfer, IS_SUCCESS)
        if (!transferFile.isNullOrEmpty()) {
            transferFile.forEach { transfer ->
                val listFile = fileCacheDataSource.getFileByIdTransfer(transfer.idTransfer)
                if (!listFile.isNullOrEmpty()) {
                    historys.add(HistoryModel(transfer, listFile))
                }
            }
        }
        emit(DataState.Success(historys.toList()))
    }
}