package volio.tech.sharefile.framework.datasource.cache.implementation

import volio.tech.sharefile.business.data.cache.abstraction.FileCacheDataSource
import volio.tech.sharefile.business.domain.FileModel
import volio.tech.sharefile.framework.datasource.cache.database.dao.FileDao
import volio.tech.sharefile.framework.datasource.cache.mappers.FileEntityMapper

class FileCacheImpl
constructor(
    private val fileDao: FileDao,
    private val fileEntityMapper: FileEntityMapper
) : FileCacheDataSource {
    override suspend fun getFile(): List<FileModel> =
        fileEntityMapper.toDomainList(fileDao.getFile())

    override suspend fun getFileById(id: Int): FileModel? =
        fileDao.getFileById(id)?.let {
            fileEntityMapper.toDomain(it)
        }

    override suspend fun getFileInPorts(tokenPorts: String): List<FileModel> =
        fileEntityMapper.toDomainList(fileDao.getFileInPorts(tokenPorts))

    override suspend fun getFileByIdTransfer(idTransfer: Long): List<FileModel> =
        fileEntityMapper.toDomainList(fileDao.getFileByIdTransfer(idTransfer))

    override suspend fun getFileByType(idTransfer: Long, type: Int): List<FileModel> =
        fileEntityMapper.toDomainList(fileDao.getFileByType(idTransfer, type))

    override suspend fun updateFile(fileModel: FileModel) {
        fileDao.updateFile(fileEntityMapper.fromDomain(fileModel))
    }

    override suspend fun addFile(fileModel: FileModel) {
        fileDao.addFile(fileEntityMapper.fromDomain(fileModel))
    }

    override suspend fun removeFile(fileModel: FileModel) {
        fileDao.removeFile(fileEntityMapper.fromDomain(fileModel))
    }

}
