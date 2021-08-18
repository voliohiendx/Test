package volio.tech.sharefile.business.domain

data class DateSelect(
    val date: String,
    val time: Long,
    var type: Int,
    var showFull: Boolean = true,
    var isSelect: Int = 0,
    var idFolder: Long = -1,
    var listIdFolder: MutableList<Long> = mutableListOf<Long>(),
)
