package volio.tech.sharefile.business.domain

data class TransferFile(
    val idTransfer: Long,
    var tokenPorts: String = "",
    var tokenTransfer: String = "",
    var timeTransfer: Long,
    var amountFile: Long,
    var sizeTransfer: Long,
    var isStatus: Int,
    var nameDeviceSent: String,
    var nameDeviceReceiver: String,
    var typeTransfer: Int,
) {
    companion object {
        const val IS_SENT = 1
        const val IS_RECEIVER = 2

        const val WAITING_TO_SEND = 0
        const val IS_PENDING = 1
        const val IS_SENDING = 2
        const val IS_FAIL = 3
        const val IS_SUCCESS = 4
    }
}