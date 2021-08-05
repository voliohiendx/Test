package volio.tech.sharefile.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import volio.tech.sharefile.framework.datasource.cache.database.AppDatabase
import volio.tech.sharefile.framework.datasource.cache.database.dao.DummyDao

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration() //TODO: update this if needed
            .build()
    }

    @Provides
    fun provideDummyDao(db: AppDatabase): DummyDao {
        return db.dummyDao()
    }

}