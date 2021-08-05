package volio.tech.sharefile.framework.datasource.cache.implementation

import volio.tech.sharefile.business.data.cache.abstraction.DummyCacheDataSource
import volio.tech.sharefile.business.domain.Dummy
import volio.tech.sharefile.framework.datasource.cache.database.dao.DummyDao
import volio.tech.sharefile.framework.datasource.cache.mappers.DummyEntityMapper

class DummyCacheImpl
constructor(
    private val dummyDao: DummyDao,
    private val dummyMapper: DummyEntityMapper
) : DummyCacheDataSource {

    override suspend fun getDummies(): List<Dummy> =
        dummyMapper.toDomainList(dummyDao.getDummies())

    override suspend fun getDummyById(id: Int): Dummy? =
        dummyDao.getDummyById(id)?.let {
            dummyMapper.toDomain(it)
        }

    override suspend fun updateDummy(dummy: Dummy) {
        dummyDao.updateDummy(dummyMapper.fromDomain(dummy))
    }

    override suspend fun addDummy(dummy: Dummy) {
        dummyDao.addDummy(dummyMapper.fromDomain(dummy))
    }

    override suspend fun removeDummy(dummy: Dummy) {
        dummyDao.removeDummy(dummyMapper.fromDomain(dummy))
    }
}
