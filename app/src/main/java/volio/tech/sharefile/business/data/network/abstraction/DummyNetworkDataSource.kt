package volio.tech.sharefile.business.data.network.abstraction

import volio.tech.sharefile.business.domain.Dummy

interface DummyNetworkDataSource {

    suspend fun getDummies(): List<Dummy>

}