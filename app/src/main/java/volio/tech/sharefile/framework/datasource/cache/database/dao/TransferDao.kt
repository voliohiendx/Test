package volio.tech.sharefile.framework.datasource.cache.database.dao

import androidx.room.*
import volio.tech.sharefile.business.domain.TransferFile
import volio.tech.sharefile.framework.datasource.cache.model.FileModelEntity
import volio.tech.sharefile.framework.datasource.cache.model.TransferFileEntity
import volio.tech.sharefile.framework.datasource.cache.model.TransferFileEntity.Companion.TABLE_NAME

@Dao
interface TransferDao {

    @Insert
    suspend fun addTransfer(transferFileEntity: TransferFileEntity)

    @Update
    suspend fun updateTransfer(transferFileEntity: TransferFileEntity)

    @Delete
    suspend fun removeTransfer(transferFileEntity: TransferFileEntity)

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getTransferFile(): List<TransferFileEntity>

    @Query("SELECT * FROM $TABLE_NAME WHERE ${TransferFileEntity.ID} = :id")
    suspend fun getTransferFileById(id: Int): TransferFileEntity?

    @Query("SELECT * FROM $TABLE_NAME WHERE ${TransferFileEntity.TYPE_TRANSFER} = :type AND ${TransferFileEntity.IS_STATUS} = :isStatus")
    suspend fun getTransferByType(type: Int, isStatus: Int): List<TransferFileEntity>

    @Query("SELECT * FROM $TABLE_NAME WHERE ${TransferFileEntity.IS_STATUS} = :status")
    suspend fun getTransferByStatus(status: Int): List<TransferFileEntity>

    @Query("SELECT * FROM $TABLE_NAME WHERE ${TransferFileEntity.TOKE_PORTS} = :tokenPort")
    suspend fun getTransferByTokenPort(tokenPort: String): List<TransferFileEntity>

}
