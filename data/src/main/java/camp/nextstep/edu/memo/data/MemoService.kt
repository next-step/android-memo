package camp.nextstep.edu.memo.data

import camp.nextstep.edu.memo.domain.entity.Memo
import java.util.UUID

class MemoService {

    private val memoList = mutableListOf<Memo>()

    fun save(memo: Memo) {
        memoList.add(memo)
    }

    fun fetch(): List<Memo> = memoList.toList()

    fun update(memo: Memo) {
        memoList.forEachIndexed { index, item ->
            if (memo.uuid != item.uuid) return@forEachIndexed
            memoList[index] = memo
        }
    }

    fun delete(uuid: UUID) {
        var removedIndex = -1
        memoList.forEachIndexed { index, item ->
            if (item.uuid != uuid) return@forEachIndexed
            removedIndex = index
        }
        if (removedIndex == -1) return
        memoList.removeAt(removedIndex)
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
