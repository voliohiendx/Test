package volio.tech.sharefile.business.data.cache.abstraction

import volio.tech.sharefile.business.domain.FileModel

interface FileCacheDataSource {

    suspend fun getFile(): List<FileModel>

    suspend fun getFileById(id: Int): FileModel?

    suspend fun getFileInPorts(tokenPorts: String): List<FileModel>

    suspend fun getFileByIdTransfer(idTransfer: Long): List<FileModel>

    suspend fun getFileByType(idTransfer: Long, type: Int): List<FileModel>

    suspend fun updateFile(fileModel: FileModel)

    suspend fun addFile(fileModel: FileModel)

    suspend fun removeFile(fileModel: FileModel)
}
