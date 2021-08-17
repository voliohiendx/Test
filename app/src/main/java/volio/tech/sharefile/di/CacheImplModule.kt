package volio.tech.sharefile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import volio.tech.sharefile.business.data.cache.abstraction.DummyCacheDataSource
import volio.tech.sharefile.business.data.cache.abstraction.FileCacheDataSource
import volio.tech.sharefile.business.data.cache.abstraction.TransferFileCacheDataSource
import volio.tech.sharefile.framework.datasource.cache.database.dao.DummyDao
import volio.tech.sharefile.framework.datasource.cache.database.dao.FileDao
import volio.tech.sharefile.framework.datasource.cache.database.dao.TransferDao
import volio.tech.sharefile.framework.datasource.cache.implementation.DummyCacheImpl
import volio.tech.sharefile.framework.datasource.cache.implementation.FileCacheImpl
import volio.tech.sharefile.framework.datasource.cache.implementation.TransferFileCacheImpl
import volio.tech.sharefile.framework.datasource.cache.mappers.DummyEntityMapper
import volio.tech.sharefile.framework.datasource.cache.mappers.FileEntityMapper
import volio.tech.sharefile.framework.datasource.cache.mappers.TransferFileEntityMapper

@InstallIn(SingletonComponent::class)
@Module
object CacheImplModule {

    @Provides
    fun provideDummyCacheDataSource(
        dummyDao: DummyDao,
        dummyEntityMapper: DummyEntityMapper
    ): DummyCacheDataSource = DummyCacheImpl(
        dummyDao,
        dummyEntityMapper
    )

    @Provides
    fun provideFileCacheDataSource(
        fileDao: FileDao,
        fileEntityMapper: FileEntityMapper
    ): FileCacheDataSource = FileCacheImpl(
        fileDao,
        fileEntityMapper
    )

    @Provides
    fun provideTransferCacheDataSource(
        transferDao: TransferDao,
        transferFileEntityMapper: TransferFileEntityMapper
    ): TransferFileCacheDataSource = TransferFileCacheImpl(
        transferDao,
        transferFileEntityMapper
    )

}
