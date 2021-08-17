package volio.tech.sharefile.framework.presentation.action

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import volio.tech.sharefile.business.data.DataState
import volio.tech.sharefile.business.domain.FileModel
import volio.tech.sharefile.business.domain.HistoryModel
import volio.tech.sharefile.business.domain.TransferFile
import volio.tech.sharefile.business.interactors.*
import javax.inject.Inject

@HiltViewModel
class TransferViewModel
@Inject
constructor(
    private val addTransfer: AddTransfer,
    private val cancelFile: CancelFile,
    private val removeFile: RemoveFile,
    private val removePort: RemovePort,
    private val removeTransfer: RemoveTransfer,
    private val updatePort: UpdatePort,
    private val updateTransfer: UpdateTransfer,
    private val getFileHistory: GetFileHistory,
    private val getTransferByTokenPort: GetTransferByTokenPort,
) : ViewModel() {

    fun addTransfer(files: List<FileModel>, onComplete: (data: TransferFile?) -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            addTransfer.addTransfer(files).collect {
                if (it is DataState.Success) {
                    withContext(Dispatchers.Main) {
                        onComplete(it.data)
                    }
                }
            }
        }

    fun cancelFile(idFile: Int, onComplete: (data: FileModel?) -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            cancelFile.cancelFile(idFile).collect {
                if (it is DataState.Success) {
                    withContext(Dispatchers.Main) {
                        onComplete(it.data)
                    }
                }
            }
        }

    fun removeFile(idFile: Int, onComplete: (data: FileModel?) -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            removeFile.removeFile(idFile).collect {
                if (it is DataState.Success) {
                    withContext(Dispatchers.Main) {
                        onComplete(it.data)
                    }
                }
            }
        }

    fun removePort(tokenPorts: String, onComplete: (data: List<TransferFile>?) -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            removePort.removePort(tokenPorts).collect {
                if (it is DataState.Success) {
                    withContext(Dispatchers.Main) {
                        onComplete(it.data)
                    }
                }
            }
        }

    fun removeTransfer(idTransfer: Int, onComplete: (data: TransferFile?) -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            removeTransfer.removeTransfer(idTransfer).collect {
                if (it is DataState.Success) {
                    withContext(Dispatchers.Main) {
                        onComplete(it.data)
                    }
                }
            }
        }

    fun updatePortPending(tokenPorts: String, onComplete: (data: List<TransferFile>?) -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            updatePort.updatePortPending(tokenPorts).collect {
                if (it is DataState.Success) {
                    withContext(Dispatchers.Main) {
                        onComplete(it.data)
                    }
                }
            }
        }

    fun updateTransfer(idTransferFile: Int, onComplete: (data: TransferFile?) -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            updateTransfer.updateTransferSuccess(idTransferFile).collect {
                if (it is DataState.Success) {
                    withContext(Dispatchers.Main) {
                        onComplete(it.data)
                    }
                }
            }
        }

    fun updatePortCancel(tokenPorts: String, onComplete: (data: List<TransferFile>?) -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            updatePort.updatePortCancel(tokenPorts).collect {
                if (it is DataState.Success) {
                    withContext(Dispatchers.Main) {
                        onComplete(it.data)
                    }
                }
            }
        }

    fun getFileHistory(typeTransfer: Int, onComplete: (data: List<HistoryModel>?) -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            getFileHistory.getFileHistory(typeTransfer).collect {
                if (it is DataState.Success) {
                    withContext(Dispatchers.Main) {
                        onComplete(it.data)
                    }
                }
            }
        }

    fun getTransferByTokenPort(tokenPort: String, onComplete: (data: List<HistoryModel>?) -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            getTransferByTokenPort.getTransferByTokenPort(tokenPort).collect {
                if (it is DataState.Success) {
                    withContext(Dispatchers.Main) {
                        onComplete(it.data)
                    }
                }
            }
        }

}