package camp.nextstep.edu.memo.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.memo.data.di.DataModuleInjector
import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository
import camp.nextstep.edu.memo.util.MemoEvent
import camp.nextstep.edu.memo.util.ViewModelEvent

class EditMemoViewModel(
    private val memoRepository: MemoRepository = DataModuleInjector.provideMemoRepository(),
    private val memoId: String
) : ViewModel() {

    @Suppress("UNCHECKED_CAST")
    class Factory(private val memoId: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return EditMemoViewModel(memoId = memoId) as T
        }
    }

    private val _actionEvent = MutableLiveData<ViewModelEvent<MemoEvent>>()
    val actionEvent: LiveData<ViewModelEvent<MemoEvent>>
        get() = _actionEvent

    private val _errorEvent = MutableLiveData<ViewModelEvent<String>>()
    val errorEvent: LiveData<ViewModelEvent<String>>
        get() = _errorEvent

    val memo = MutableLiveData<String>()

    init {
        loadMemo()
    }

    private fun loadMemo() {
        memo.value = memoRepository.findMemo(memoId)?.value ?: ""
    }

    fun editMemo() {
        val result = memoRepository.editMemo(Memo(memoId, memo.value ?: ""))
        if (result.isSuccess) {
            _actionEvent.value = ViewModelEvent(MemoEvent.Complete)
        } else {
            _errorEvent.value = ViewModelEvent(result.exceptionOrNull()?.localizedMessage ?: "")
        }
    }

    fun cancel() {
        _actionEvent.value = ViewModelEvent(MemoEvent.Cancel)
    }
}