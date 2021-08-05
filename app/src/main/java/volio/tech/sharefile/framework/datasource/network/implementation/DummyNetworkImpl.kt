package volio.tech.sharefile.framework.datasource.network.implementation

import volio.tech.sharefile.business.data.network.abstraction.DummyNetworkDataSource
import volio.tech.sharefile.business.domain.Dummy
import volio.tech.sharefile.framework.datasource.network.api.DummyApi
import volio.tech.sharefile.framework.datasource.network.mappers.DummyDtoMapper

class DummyNetworkImpl
constructor(
    private val dummyApi: DummyApi,
    private val dummyDtoMapper: DummyDtoMapper
) : DummyNetworkDataSource {

    override suspend fun getDummies(): List<Dummy> =
        dummyDtoMapper.toDomainList(dummyApi.getDummies())

}