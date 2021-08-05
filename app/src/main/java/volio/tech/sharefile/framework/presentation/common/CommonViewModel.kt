package volio.tech.sharefile.framework.presentation.common

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import volio.tech.sharefile.business.data.DataState
import volio.tech.sharefile.business.domain.Dummy
import volio.tech.sharefile.business.interactors.GetDummies
import javax.inject.Inject

@HiltViewModel
class CommonViewModel
@Inject
constructor(
    private val savedStateHandler: SavedStateHandle,
    private val getDummies: GetDummies
) : ViewModel() {

    private val _dummies = MutableLiveData<DataState<List<Dummy>>>()
    val dummies: LiveData<DataState<List<Dummy>>>
        get() = _dummies

    fun getDummies() = viewModelScope.launch {
        withContext(IO) {
            getDummies.getDummies(true).collect { dataState ->
                withContext(Main) {
                    _dummies.value = dataState
                }
            }
        }
    }

}