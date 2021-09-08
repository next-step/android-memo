package camp.nextstep.edu.memo

import androidx.lifecycle.ViewModel
import camp.nextstep.edu.domain.MemoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val memoRepository: MemoRepository
) : ViewModel() {
    fun doSomething() {
        memoRepository.findMemos()
    }
}
