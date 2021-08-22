package camp.nextstep.edu.memo.main

import androidx.lifecycle.ViewModel
import camp.nextstep.edu.memo.MemoEvent
import camp.nextstep.edu.memo.data.MemoRepositoryImpl
import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    private val memoRepository: MemoRepository = MemoRepositoryImpl.getInstance()
) : ViewModel() {

    private val _memoList = MutableStateFlow<List<Memo>>(emptyList())
    val memoList: StateFlow<List<Memo>> get() = _memoList.asStateFlow()

    private val _memoEvent = MutableStateFlow<MemoEvent>(MemoEvent.None)
    val memoEvent: StateFlow<MemoEvent> get() = _memoEvent.asStateFlow()

    fun fetchMemoList() {
        _memoList.value = memoRepository.fetch()
    }

    fun delete(position: Int) {
        memoRepository.delete(position)
        _memoEvent.value = MemoEvent.Delete(position)
    }
}
