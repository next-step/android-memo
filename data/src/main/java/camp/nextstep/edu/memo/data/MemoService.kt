package camp.nextstep.edu.memo.data

import camp.nextstep.edu.memo.domain.entity.Memo

class MemoService {

    private val _memoList = mutableListOf<Memo>()

    fun save(memo: Memo) {
        _memoList.add(memo)
    }

    fun fetch(): List<Memo> = _memoList.toList()

    fun update(position: Int, memo: Memo) {
        _memoList[position] = memo
    }

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
