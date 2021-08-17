package volio.tech.sharefile.framework.datasource.cache.mappers

import volio.tech.sharefile.business.domain.FileModel
import volio.tech.sharefile.framework.datasource.DomainMapper
import volio.tech.sharefile.framework.datasource.cache.model.FileModelEntity
import javax.inject.Inject

class FileEntityMapper
@Inject
constructor() : DomainMapper<FileModelEntity, FileModel> {

    override fun toDomain(model: FileModelEntity): FileModel {
        return FileModel(
            id = model.id,
            idFolder = model.idFolder,
            type = model.type,
            typeDoc = model.typeDoc,
            name = model.name,
            uri = model.uri,
            path = model.path,
            size = model.size,
            duration = model.duration,
            packageName = model.packageName,
            timeCreated = model.timeCreated,
            idDatabase = model.idDatabase,
            tokenPorts = model.tokenPorts,
            tokenransfer = model.tokenransfer,
            idTransfer = model.idTransfer,
            isStatusTransfer = model.isStatusTransfer,
            linkThumb = model.linkThumb,
            progression = model.progression
        )
    }

    override fun fromDomain(domainModel: FileModel): FileModelEntity {
        return FileModelEntity(
            id = domainModel.id,
            idFolder = domainModel.idFolder,
            type = domainModel.type,
            typeDoc = domainModel.typeDoc,
            name = domainModel.name,
            uri = domainModel.uri,
            path = domainModel.path,
            size = domainModel.size,
            duration = domainModel.duration,
            packageName = domainModel.packageName,
            timeCreated = domainModel.timeCreated,
            idDatabase = domainModel.idDatabase,
            tokenPorts = domainModel.tokenPorts,
            tokenransfer = domainModel.tokenransfer,
            idTransfer = domainModel.idTransfer,
            isStatusTransfer = domainModel.isStatusTransfer,
            linkThumb = domainModel.linkThumb,
            progression = domainModel.progression
        )
    }

    fun toDomainList(list: List<FileModelEntity>): List<FileModel> = list.map {
        toDomain(it)
    }

    fun fromDomainList(list: List<FileModel>): List<FileModelEntity> = list.map {
        fromDomain(it)
    }

}
