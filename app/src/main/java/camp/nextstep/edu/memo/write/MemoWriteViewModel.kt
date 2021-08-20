package camp.nextstep.edu.memo.write

import androidx.lifecycle.ViewModel
import camp.nextstep.edu.memo.MemoEvent
import camp.nextstep.edu.memo.data.MemoRepositoryImpl
import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MemoWriteViewModel(
    private val memoRepository: MemoRepository = MemoRepositoryImpl.getInstance()
) : ViewModel() {

    private val _isSavedMemo = MutableStateFlow<MemoEvent>(MemoEvent.None)
    val isSavedMemo: StateFlow<MemoEvent> get() = _isSavedMemo.asStateFlow()

    fun saveMemo(memo: String) {
        if (memo.isEmpty()) {
            _isSavedMemo.value = MemoEvent.Cancel
            return
        }
        memoRepository.save(memo = Memo(memo))
        _isSavedMemo.value = MemoEvent.Write
    }

    fun cancel() {
        _isSavedMemo.value = MemoEvent.Cancel
    }
}
