package volio.tech.sharefile.business.domain

data class DataLocal(
    val file: MutableList<FileModel> = mutableListOf<FileModel>(),
    val folder: MutableList<Folder> = mutableListOf<Folder>(),
    val listDate: MutableList<DateSelect> = mutableListOf<DateSelect>()
)