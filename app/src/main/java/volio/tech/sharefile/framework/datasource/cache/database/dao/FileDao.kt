package volio.tech.sharefile.framework.datasource.cache.database.dao

import androidx.room.*
import volio.tech.sharefile.framework.datasource.cache.model.FileModelEntity
import volio.tech.sharefile.framework.datasource.cache.model.FileModelEntity.Companion.ID_TRANSFER
import volio.tech.sharefile.framework.datasource.cache.model.FileModelEntity.Companion.TABLE_NAME

@Dao
interface FileDao {

    @Insert
    suspend fun addFile(fileModelEntity: FileModelEntity)

    @Update
    suspend fun updateFile(fileModelEntity: FileModelEntity)

    @Delete
    suspend fun removeFile(fileModelEntity: FileModelEntity)

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getFile(): List<FileModelEntity>

    @Query("SELECT * FROM $TABLE_NAME WHERE ${FileModelEntity.ID_DATABASE} = :id")
    suspend fun getFileById(id: Int): FileModelEntity?

    @Query("SELECT * FROM $TABLE_NAME WHERE ${FileModelEntity.TOKEN_PORTS} = :tokenPorts")
    suspend fun getFileInPorts(tokenPorts: String): List<FileModelEntity>

    @Query("SELECT * FROM $TABLE_NAME WHERE ${FileModelEntity.ID_TRANSFER} = :idTransfer")
    suspend fun getFileByIdTransfer(idTransfer: Long): List<FileModelEntity>

    @Query("SELECT * FROM $TABLE_NAME WHERE ${FileModelEntity.TYPE} = :type AND $ID_TRANSFER = :idTransfer")
    suspend fun getFileByType(idTransfer: Long, type: Int): List<FileModelEntity>

}
