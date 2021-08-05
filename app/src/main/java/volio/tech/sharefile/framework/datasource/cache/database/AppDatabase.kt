package volio.tech.sharefile.framework.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import volio.tech.sharefile.framework.datasource.cache.database.dao.DummyDao
import volio.tech.sharefile.framework.datasource.cache.model.DummyEntity

@Database(
    entities = [
        DummyEntity::class
    ],
    version = 1
)

//@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dummyDao(): DummyDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}