package camp.nextstep.edu.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import camp.nextstep.edu.domain.Memo
import camp.nextstep.edu.domain.MemosSource

/**
 * Created By Malibin
 * on 8월 26, 2021
 */

class MainViewModel(
    private val memosRepository: MemosSource,
) {
    private val _memos = MutableLiveData<List<Memo>>()
    val memos: LiveData<List<Memo>> = _memos

    fun loadAllMemos() {
        _memos.value = memosRepository.getAllMemos()
    }
}
