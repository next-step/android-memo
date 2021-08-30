package camp.nextstep.edu.memo.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.memo.data.MemoRepositoryImpl
import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository
import camp.nextstep.edu.memo.util.MemoEvent
import camp.nextstep.edu.memo.util.ViewModelEvent

class EditMemoViewModel(
    private val memoRepository: MemoRepository = MemoRepositoryImpl.instance,
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

    val memo = MutableLiveData<String>()

    init {
        loadMemo()
    }

    private fun loadMemo() {
        memo.value = memoRepository.getMemo(memoId)?.value ?: ""
    }

    fun editMemo() {
        memoRepository.editMemo(Memo(memoId, memo.value ?: ""))
        _actionEvent.value = ViewModelEvent(MemoEvent.Complete)
    }

    fun cancel() {
        _actionEvent.value = ViewModelEvent(MemoEvent.Cancel)
    }
}