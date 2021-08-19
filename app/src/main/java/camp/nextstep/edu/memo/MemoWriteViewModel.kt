package camp.nextstep.edu.memo

import androidx.lifecycle.ViewModel
import camp.nextstep.edu.memo.data.MemoRepositoryImpl
import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository

class MemoWriteViewModel(
    private val memoRepository: MemoRepository = MemoRepositoryImpl()
) : ViewModel() {

    fun saveMemo(memo: String) {
        memoRepository.save(memo = Memo(memo))
    }
}
