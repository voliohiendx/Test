package volio.tech.sharefile.framework.datasource.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import volio.tech.sharefile.framework.datasource.cache.model.FileModelEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class FileModelEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_DATABASE)
    var idDatabase: Long = 0,

    @ColumnInfo(name = ID)
    var id: Long,

    @ColumnInfo(name = ID_FOLDER)
    var idFolder: Long,

    @ColumnInfo(name = TYPE)
    var type: Int,

    @ColumnInfo(name = TYPE_DOC)
    var typeDoc: String = "",

    @ColumnInfo(name = NAME)
    var name: String,

    @ColumnInfo(name = URI)
    var uri: String,

    @ColumnInfo(name = PATH)
    var path: String,

    @ColumnInfo(name = SIZE)
    var size: Long = 0L,

    @ColumnInfo(name = DURATION)
    var duration: Long = 0L,

    @ColumnInfo(name = PACKAGE_NAME)
    var packageName: String = "",

    @ColumnInfo(name = TIME_CREATED)
    var timeCreated: Long,

    @ColumnInfo(name = TOKEN_TRANSFER)
    var tokenransfer: String = "",

    @ColumnInfo(name = TOKEN_PORTS)
    var tokenPorts: String = "",

    @ColumnInfo(name = ID_TRANSFER)
    var idTransfer: Long = 0,

    @ColumnInfo(name = IS_STATUS_TRANSFER)
    var isStatusTransfer: Int,

    @ColumnInfo(name = LINK_THUMB)
    var linkThumb: String,

    @ColumnInfo(name = PROGRESSION)
    var progression: Float
) {

    companion object {
        const val TABLE_NAME = "FileModelEntity"
        const val ID_DATABASE = "idDatabase"
        const val ID = "id"
        const val ID_TRANSFER = "idTransfer"
        const val ID_FOLDER = "idFolder"
        const val TYPE = "type"
        const val TYPE_DOC = "typeDoc"
        const val NAME = "name"
        const val URI = "uri"
        const val PATH = "path"
        const val SIZE = "size"
        const val DURATION = "duration"
        const val PACKAGE_NAME = "packagename"
        const val TIME_CREATED = "timeCreated"
        const val TOKEN_TRANSFER = "tokenTransfer"
        const val TOKEN_PORTS = "tokenPorts"
        const val IS_STATUS_TRANSFER = "isStatusTransfer"
        const val LINK_THUMB = "linkThumb"
        const val PROGRESSION = "progression"
    }

}