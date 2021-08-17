package volio.tech.sharefile.business.domain

data class HistoryModel(
    val transferFile: TransferFile,
    val file: List<FileModel>
)