package camp.nextstep.edu.memo.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.domain.Memo
import camp.nextstep.edu.domain.MemosRepository
import camp.nextstep.edu.memo.R
import camp.nextstep.edu.memo.utils.SingleLiveEvent
import java.util.*

/**
 * Created By Malibin
 * on 8월 26, 2021
 */

class EditMemoViewModel(
    private val memosRepository: MemosRepository,
) : ViewModel() {
    private val _memoSaved = MutableLiveData<Unit>()
    val memoSaved: LiveData<Unit> = _memoSaved

    private val _toastMessage = SingleLiveEvent<Int>()
    val toastMessage: LiveData<Int> = _toastMessage

    val title = MutableLiveData("")
    val content = MutableLiveData("")
    var memoId: String? = null

    fun saveMemo() {
        val title = this.title.value.orEmpty()
        if (title.isBlank()) {
            _toastMessage.value = R.string.fill_memo_title
            return
        }
        val content = this.content.value.orEmpty()
        val memo = Memo(
            title = title,
            content = content,
            id = memoId ?: UUID.randomUUID().toString()
        )
        memosRepository.save(memo)
        _memoSaved.value = Unit
    }

    fun loadMemo(memoId: String? = null) {
        if (memoId.isNullOrBlank()) return
        val memo = memosRepository.getMemo(memoId) ?: return
        this.title.value = memo.title
        this.content.value = memo.content
        this.memoId = memo.id
    }
}
