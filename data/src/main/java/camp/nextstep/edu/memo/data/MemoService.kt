package camp.nextstep.edu.memo.data

import camp.nextstep.edu.memo.domain.entity.Memo

class MemoService {

    private val _memoList = mutableListOf<Memo>()
    val memoList: List<Memo> get() = _memoList

    fun save(memo: Memo) {
        _memoList.add(memo)
    }

    fun fetch(): List<Memo> = memoList.toList()

    companion object {
        @Volatile
        private var instance: MemoService? = null

        fun getInstance(): MemoService = instance ?: synchronized(this) {
            instance ?: MemoService().also {
                instance = it
            }
        }
    }
}
