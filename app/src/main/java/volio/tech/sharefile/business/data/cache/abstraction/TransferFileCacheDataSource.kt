package volio.tech.sharefile.business.data.cache.abstraction

import volio.tech.sharefile.business.domain.TransferFile

interface TransferFileCacheDataSource {

    suspend fun getTransferFile(): List<TransferFile>

    suspend fun getTransferFileById(id: Int): TransferFile?

    suspend fun getTransferByType(type: Int, isStatus: Int): List<TransferFile>

    suspend fun getTransferByTokenPort(tokenPort: String): List<TransferFile>

    suspend fun getTransferByStatus(status: Int): List<TransferFile>

    suspend fun updateTransferFile(transferFile: TransferFile)

    suspend fun addTransferFile(transferFile: TransferFile)

    suspend fun removeTransferFile(transferFile: TransferFile)
}
