package volio.tech.sharefile.business.domain

data class FileModel(
    var id: Long = -1,
    var idFolder: Long,
    var type: Int,
    var typeDoc: String = "",
    var name: String,
    var uri: String,
    var path: String,
    var size: Long = 0L,
    var duration: Long = 0L,
    var packageName: String = "",
    var timeCreated: Long,
    var idDatabase: Long = 0,
    var tokenransfer: String = "",
    var tokenPorts: String = "",
    var idTransfer: Long = 0,
    var isStatusTransfer: Int = 0,
    var linkThumb: String = "",
    var progression: Float = 0f,
    var dateCreated: String = "",
) {
    companion object {
        const val IS_IMAGE = 1
        const val IS_VIDEO = 2
        const val IS_APP = 3
        const val IS_APK = 6
        const val IS_DOC = 4
        const val IS_MUSIC = 5
        const val IS_OTHER = 7

        const val PDF = "pdf"
        const val WORD = "word"
        const val EXCEL = "excel"
        const val POWERPOINT = "powerpoint"

        const val WAITING_TO_SEND = 0
        const val IS_SUCCESS = 1
        const val IS_PENDING = 2
        const val IS_SENDING = 5
        const val IS_CANCEL = 3
        const val IS_FAILED = 4
        // const val IS_SUCCESS= 1
    }
}
