package camp.nextstep.edu.memo.write

import androidx.lifecycle.ViewModel
import camp.nextstep.edu.memo.MemoEvent
import camp.nextstep.edu.memo.data.MemoRepositoryImpl
import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository
import java.util.UUID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MemoWriteViewModel(
    private val memoRepository: MemoRepository = MemoRepositoryImpl.getInstance()
) : ViewModel() {

    private val _memoEvent = MutableStateFlow<MemoEvent>(MemoEvent.None)
    val memoEvent: StateFlow<MemoEvent> get() = _memoEvent.asStateFlow()

    fun saveMemo(uuid: UUID, memo: String) {
        if (memo.isEmpty()) {
            _memoEvent.value = MemoEvent.Cancel
            return
        }
        memoRepository.save(memo = Memo(uuid = uuid, value = memo))
        _memoEvent.value = MemoEvent.Write
    }

    fun cancel() {
        _memoEvent.value = MemoEvent.Cancel
    }
}
