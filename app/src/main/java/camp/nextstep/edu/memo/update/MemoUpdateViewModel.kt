package camp.nextstep.edu.memo.update

import androidx.lifecycle.ViewModel
import camp.nextstep.edu.memo.MemoEvent
import camp.nextstep.edu.memo.data.MemoRepositoryImpl
import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository
import java.util.UUID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MemoUpdateViewModel(
    private val memoRepository: MemoRepository = MemoRepositoryImpl.getInstance()
) : ViewModel() {

    private val _memoEvent = MutableStateFlow<MemoEvent>(MemoEvent.None)
    val memoEvent: StateFlow<MemoEvent> get() = _memoEvent.asStateFlow()

    fun update(uuid: UUID, memo: String) {
        memoRepository.update(memo = Memo(uuid = uuid, value = memo))
        _memoEvent.value = MemoEvent.Update
    }
}
