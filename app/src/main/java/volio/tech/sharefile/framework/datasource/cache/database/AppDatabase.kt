package volio.tech.sharefile.framework.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import volio.tech.sharefile.framework.datasource.cache.database.dao.DummyDao
import volio.tech.sharefile.framework.datasource.cache.database.dao.FileDao
import volio.tech.sharefile.framework.datasource.cache.database.dao.TransferDao
import volio.tech.sharefile.framework.datasource.cache.model.DummyEntity
import volio.tech.sharefile.framework.datasource.cache.model.FileModelEntity
import volio.tech.sharefile.framework.datasource.cache.model.TransferFileEntity

@Database(
    entities = [
        DummyEntity::class,
        FileModelEntity::class,
        TransferFileEntity::class
    ],
    version = 1
)

//@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dummyDao(): DummyDao
    abstract fun fileDao(): FileDao
    abstract fun transferDao(): TransferDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}