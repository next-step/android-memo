package camp.nextstep.edu.memo

import androidx.lifecycle.ViewModel
import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(
    private val memoRepository: MemoRepository
) : ViewModel() {

    private val _memoList = MutableStateFlow<List<Memo>>(emptyList())
    val memoList: StateFlow<List<Memo>> get() = _memoList

    fun fetchMemoList() {
        _memoList.value = memoRepository.fetch()
    }
}
