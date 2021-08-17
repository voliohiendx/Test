package volio.tech.sharefile.framework.datasource.cache.implementation

import volio.tech.sharefile.business.data.cache.abstraction.TransferFileCacheDataSource
import volio.tech.sharefile.business.domain.TransferFile
import volio.tech.sharefile.framework.datasource.cache.database.dao.TransferDao
import volio.tech.sharefile.framework.datasource.cache.mappers.TransferFileEntityMapper

class TransferFileCacheImpl
constructor(
    private val transferDao: TransferDao,
    private val transferFileEntityMapper: TransferFileEntityMapper
) : TransferFileCacheDataSource {

    override suspend fun getTransferFile(): List<TransferFile> =
        transferFileEntityMapper.toDomainList(transferDao.getTransferFile())

    override suspend fun getTransferFileById(id: Int): TransferFile? =
        transferDao.getTransferFileById(id)?.let {
            transferFileEntityMapper.toDomain(it)
        }

    override suspend fun getTransferByType(type: Int, isStatus: Int): List<TransferFile> =
        transferFileEntityMapper.toDomainList(transferDao.getTransferByType(type, isStatus))

    override suspend fun getTransferByTokenPort(tokenPort: String): List<TransferFile> =
        transferFileEntityMapper.toDomainList(transferDao.getTransferByTokenPort(tokenPort))

    override suspend fun getTransferByStatus(status: Int): List<TransferFile> =
        transferFileEntityMapper.toDomainList(transferDao.getTransferByStatus(status))

    override suspend fun updateTransferFile(transferFile: TransferFile) {
        transferDao.updateTransfer(transferFileEntityMapper.fromDomain(transferFile))
    }

    override suspend fun addTransferFile(transferFile: TransferFile) {
        transferDao.addTransfer(transferFileEntityMapper.fromDomain(transferFile))
    }

    override suspend fun removeTransferFile(transferFile: TransferFile) {
        transferDao.removeTransfer(transferFileEntityMapper.fromDomain(transferFile))
    }
}
