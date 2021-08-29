package camp.nextstep.edu.memo.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import camp.nextstep.edu.domain.Memo
import camp.nextstep.edu.domain.MemosSource
import camp.nextstep.edu.memo.R
import camp.nextstep.edu.memo.utils.SingleLiveEvent

/**
 * Created By Malibin
 * on 8월 26, 2021
 */

class EditMemoViewModel(
    private val memosRepository: MemosSource,
) {
    private val _memoSaved = SingleLiveEvent<Unit>()
    val memoSaved: LiveData<Unit> = _memoSaved

    private val _toastMessage = SingleLiveEvent<Int>()
    val toastMessage: LiveData<Int> = _toastMessage

    val title = MutableLiveData("")
    val content = MutableLiveData("")

    fun saveMemo() {
        val title = this.title.value.orEmpty()
        if(title.isBlank()){
            _toastMessage.value = R.string.fill_memo_title
            return
        }
        val content = this.content.value.orEmpty()
        val memo = Memo(
            title = title,
            content = content,
        )
        memosRepository.save(memo)
        _memoSaved.call()
    }
}
