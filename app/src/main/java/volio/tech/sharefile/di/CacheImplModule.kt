package volio.tech.sharefile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import volio.tech.sharefile.business.data.cache.abstraction.DummyCacheDataSource
import volio.tech.sharefile.framework.datasource.cache.database.dao.DummyDao
import volio.tech.sharefile.framework.datasource.cache.implementation.DummyCacheImpl
import volio.tech.sharefile.framework.datasource.cache.mappers.DummyEntityMapper

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

}
