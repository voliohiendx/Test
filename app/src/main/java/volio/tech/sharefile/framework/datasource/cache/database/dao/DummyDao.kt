package volio.tech.sharefile.framework.datasource.cache.database.dao

import androidx.room.*
import volio.tech.sharefile.framework.datasource.cache.model.DummyEntity
import volio.tech.sharefile.framework.datasource.cache.model.DummyEntity.Companion.ID
import volio.tech.sharefile.framework.datasource.cache.model.DummyEntity.Companion.TABLE_NAME

@Dao
interface DummyDao {

    @Insert
    suspend fun addDummy(dummy: DummyEntity)

    @Update
    suspend fun updateDummy(dummy: DummyEntity)

    @Delete
    suspend fun removeDummy(dummy: DummyEntity)

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getDummies(): List<DummyEntity>

    @Query("SELECT * FROM $TABLE_NAME WHERE $ID = :id")
    suspend fun getDummyById(id: Int): DummyEntity?

}
