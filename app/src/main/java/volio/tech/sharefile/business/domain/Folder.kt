package volio.tech.sharefile.business.domain

data class Folder(
    val id: Long,
    val name: String,
    var size: Int,
    var showFull: Boolean = false,
)