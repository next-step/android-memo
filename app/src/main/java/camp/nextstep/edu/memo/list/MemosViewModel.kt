package camp.nextstep.edu.memo.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.memo.data.di.DataModuleInjector
import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository

class MemosViewModel(
    private val memoRepository: MemoRepository = DataModuleInjector.provideMemoRepository()
) : ViewModel() {

    private val _memoList = MutableLiveData<List<Memo>>()
    val memoList: LiveData<List<Memo>>
        get() = _memoList

    init {
        loadMemoList()
    }

    fun deleteMemo(memoId: String) {
        memoRepository.deleteMemo(memoId)
        loadMemoList()
    }

    private fun loadMemoList() {
        _memoList.value = memoRepository.getMemoList()
    }
}