package volio.tech.sharefile.business.interactors

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import volio.tech.sharefile.business.data.DataState
import volio.tech.sharefile.business.data.cache.abstraction.FileCacheDataSource
import volio.tech.sharefile.business.data.cache.abstraction.TransferFileCacheDataSource
import volio.tech.sharefile.business.domain.FileModel
import volio.tech.sharefile.business.domain.TransferFile
import volio.tech.sharefile.business.domain.TransferFile.Companion.IS_PENDING
import javax.inject.Inject

class AddTransfer
@Inject
constructor(
    private val fileCacheDataSource: FileCacheDataSource,
    private val transferFileCacheDataSource: TransferFileCacheDataSource
) {

    suspend fun addTransfer(files: List<FileModel>): Flow<DataState<TransferFile>> = flow {
        emit(DataState.Loading())
        val timeTransfer = System.currentTimeMillis()
        val tokenPorts = "hienDxTest"
        val tokenTransfer = ""
        val amountFile = files.size.toLong()
        var isStatus = IS_PENDING
        var nameDeviceSent = ""
        var nameDeviceReceiver = ""
        var sizeTransfer: Long = 0L
        var typeTransfer: Int = 0
        files.forEach {
            sizeTransfer += it.size
        }
        val transferFile = TransferFile(
            idTransfer = 0,
            tokenPorts = tokenPorts,
            tokenTransfer = tokenTransfer,
            timeTransfer = timeTransfer,
            amountFile = amountFile,
            sizeTransfer = sizeTransfer,
            isStatus = isStatus,
            nameDeviceSent = nameDeviceSent,
            nameDeviceReceiver = nameDeviceReceiver,
            typeTransfer = typeTransfer,
        )

        transferFileCacheDataSource.addTransferFile(transferFile)
        val transferFileAdded =
            transferFileCacheDataSource.getTransferFile().sortedByDescending { it.idTransfer }[0]

        files.forEach {
            it.tokenPorts = tokenPorts
            it.tokenransfer = tokenTransfer
            it.idDatabase = 0
            it.idTransfer = transferFileAdded.idTransfer
            it.isStatusTransfer = FileModel.IS_PENDING
            fileCacheDataSource.addFile(it)
        }

        emit(DataState.Success(transferFileAdded))

    }

}
