package volio.tech.sharefile.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import volio.tech.sharefile.framework.datasource.cache.database.AppDatabase
import volio.tech.sharefile.framework.datasource.cache.database.dao.DummyDao
import volio.tech.sharefile.framework.datasource.cache.database.dao.FileDao
import volio.tech.sharefile.framework.datasource.cache.database.dao.TransferDao

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .enableMultiInstanceInvalidation()
                .setJournalMode(RoomDatabase.JournalMode.AUTOMATIC)
                .build()

            INSTANCE = instance
            instance
        }
    }

    @Provides
    fun provideDummyDao(db: AppDatabase): DummyDao {
        return db.dummyDao()
    }

    @Provides
    fun provideFileDao(db: AppDatabase): FileDao {
        return db.fileDao()
    }

    @Provides
    fun provideTransferDao(db: AppDatabase): TransferDao {
        return db.transferDao()
    }

}
