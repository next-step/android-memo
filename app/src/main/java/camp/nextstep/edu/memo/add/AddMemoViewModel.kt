package camp.nextstep.edu.memo.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.memo.data.di.DataModuleInjector
import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository
import camp.nextstep.edu.memo.util.MemoEvent
import camp.nextstep.edu.memo.util.ViewModelEvent

class AddMemoViewModel(
    private val memoRepository: MemoRepository = DataModuleInjector.provideMemoRepository()
) : ViewModel() {

    private val _actionEvent = MutableLiveData<ViewModelEvent<MemoEvent>>()
    val actionEvent: LiveData<ViewModelEvent<MemoEvent>>
        get() = _actionEvent

    val memo = MutableLiveData<String>()

    fun addMemo() {
        memoRepository.addMemo(Memo.newInstance(memo.value ?: ""))
        _actionEvent.value = ViewModelEvent(MemoEvent.Complete)
    }

    fun cancel() {
        _actionEvent.value = ViewModelEvent(MemoEvent.Cancel)
    }
}
