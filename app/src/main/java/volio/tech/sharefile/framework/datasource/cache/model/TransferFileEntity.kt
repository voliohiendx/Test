package volio.tech.sharefile.framework.datasource.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import volio.tech.sharefile.framework.datasource.cache.model.TransferFileEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class TransferFileEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val idTransfer: Long,

    @ColumnInfo(name = TOKE_PORTS)
    var tokenPorts: String = "",

    @ColumnInfo(name = TOKEN_TRANSFER)
    var tokenransfer: String = "",

    @ColumnInfo(name = TIME_TRANSFER)
    var timeTransfer: Long,

    @ColumnInfo(name = AMOUNT_FILE)
    var amountFile: Long,

    @ColumnInfo(name = SIZE_TRANSFER)
    var sizeTransfer: Long,

    @ColumnInfo(name = IS_STATUS)
    var isStatus: Int,

    @ColumnInfo(name = NAME_DEVICE_SEND)
    var nameDeviceSent: String,

    @ColumnInfo(name = NAME_DEVICE_RECEIVER)
    var nameDeviceReceiver: String,

    @ColumnInfo(name = TYPE_TRANSFER)
    var typeTransfer: Int
) {

    companion object {
        const val TABLE_NAME = "Transfer"
        const val ID = "idTransfer"
        const val TOKE_PORTS = "tokenPorts"
        const val TIME_TRANSFER = "timeTransfer"
        const val AMOUNT_FILE = "amountFile"
        const val TOKEN_TRANSFER = "tokenTransfer"
        const val SIZE_TRANSFER = "sizeTransfer"
        const val IS_STATUS = "isStatus"
        const val NAME_DEVICE_SEND = "nameDeviceSent"
        const val NAME_DEVICE_RECEIVER = "nameDeviceReceiver"
        const val TYPE_TRANSFER = "typeTransfer"
    }

}