package volio.tech.sharefile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import volio.tech.sharefile.business.data.network.abstraction.DummyNetworkDataSource
import volio.tech.sharefile.framework.datasource.network.api.DummyApi
import volio.tech.sharefile.framework.datasource.network.implementation.DummyNetworkImpl
import volio.tech.sharefile.framework.datasource.network.mappers.DummyDtoMapper

@InstallIn(SingletonComponent::class)
@Module
object NetworkImplModule {

    @Provides
    fun provideDummyNetworkDataSource(
        dummyApi: DummyApi,
        dummyDtoMapper: DummyDtoMapper
    ): DummyNetworkDataSource =
        DummyNetworkImpl(
            dummyApi, dummyDtoMapper
        )

}