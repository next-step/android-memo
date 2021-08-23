package camp.nextstep.edu.memo.data

import camp.nextstep.edu.memo.domain.entity.Memo
import java.util.UUID

internal class MemoServiceImpl : MemoService {

    private val memoList = mutableListOf<Memo>()

    override fun save(memo: Memo) {
        memoList.add(memo)
    }

    override fun fetch(): List<Memo> = memoList.toList()

    override fun update(memo: Memo) {
        memoList.forEachIndexed { index, item ->
            if (memo.uuid != item.uuid) return@forEachIndexed
            memoList[index] = memo
        }
    }

    override fun delete(uuid: UUID) {
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
        private var instance: MemoServiceImpl? = null

        fun getInstance(): MemoServiceImpl = instance ?: synchronized(this) {
            instance ?: MemoServiceImpl().also {
                instance = it
            }
        }
    }
}
