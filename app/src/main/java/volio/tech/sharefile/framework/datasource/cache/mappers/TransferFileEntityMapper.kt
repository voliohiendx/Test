package volio.tech.sharefile.framework.datasource.cache.mappers

import volio.tech.sharefile.business.domain.TransferFile
import volio.tech.sharefile.framework.datasource.DomainMapper
import volio.tech.sharefile.framework.datasource.cache.model.TransferFileEntity
import javax.inject.Inject

class TransferFileEntityMapper
@Inject
constructor() : DomainMapper<TransferFileEntity, TransferFile> {

    override fun toDomain(model: TransferFileEntity): TransferFile {
        return TransferFile(
            idTransfer = model.idTransfer,
            tokenPorts = model.tokenPorts,
            tokenTransfer = model.tokenransfer,
            timeTransfer = model.timeTransfer,
            amountFile = model.amountFile,
            sizeTransfer = model.sizeTransfer,
            isStatus = model.isStatus,
            nameDeviceSent = model.nameDeviceSent,
            nameDeviceReceiver = model.nameDeviceReceiver,
            typeTransfer = model.typeTransfer
        )
    }

    override fun fromDomain(domainModel: TransferFile): TransferFileEntity {
        return TransferFileEntity(
            idTransfer = domainModel.idTransfer,
            tokenPorts = domainModel.tokenPorts,
            tokenransfer = domainModel.tokenTransfer,
            timeTransfer = domainModel.timeTransfer,
            amountFile = domainModel.amountFile,
            sizeTransfer = domainModel.sizeTransfer,
            isStatus = domainModel.isStatus,
            nameDeviceSent = domainModel.nameDeviceSent,
            nameDeviceReceiver = domainModel.nameDeviceReceiver,
            typeTransfer = domainModel.typeTransfer
        )
    }

    fun toDomainList(list: List<TransferFileEntity>): List<TransferFile> = list.map {
        toDomain(it)
    }

    fun fromDomainList(list: List<TransferFile>): List<TransferFileEntity> = list.map {
        fromDomain(it)
    }


}