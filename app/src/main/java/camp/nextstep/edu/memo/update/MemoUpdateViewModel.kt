package camp.nextstep.edu.memo.update

import androidx.lifecycle.ViewModel
import camp.nextstep.edu.memo.MemoEvent
import camp.nextstep.edu.memo.data.MemoRepositoryImpl
import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MemoUpdateViewModel(
    private val memoRepository: MemoRepository = MemoRepositoryImpl.getInstance()
) : ViewModel() {

    private val _memoEvent = MutableStateFlow<MemoEvent>(MemoEvent.None)
    val memoEvent: StateFlow<MemoEvent> get() = _memoEvent.asStateFlow()

    fun update(position: Int, memo: String) {
        memoRepository.update(position = position, memo = Memo(memo))
        _memoEvent.value = MemoEvent.Update
    }
}
