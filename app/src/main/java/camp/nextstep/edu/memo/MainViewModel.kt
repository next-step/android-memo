package camp.nextstep.edu.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.domain.Memo
import camp.nextstep.edu.domain.MemosRepository

/**
 * Created By Malibin
 * on 8월 26, 2021
 */

class MainViewModel(
    private val memosRepository: MemosRepository,
) : ViewModel() {
    private val _memos = MutableLiveData<List<Memo>>()
    val memos: LiveData<List<Memo>> = _memos

    fun loadAllMemos() {
        _memos.value = memosRepository.getAllMemos()
    }

    fun deleteMemo(memoId: String) {
        memosRepository.deleteMemo(memoId)
        val currentMemos = _memos.value.orEmpty()
        val memo = currentMemos.find { it.id == memoId } ?: return
        _memos.value = currentMemos - memo
    }
}
