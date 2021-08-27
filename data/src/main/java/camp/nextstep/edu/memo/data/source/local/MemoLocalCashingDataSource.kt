package camp.nextstep.edu.memo.data.source.local

import camp.nextstep.edu.memo.data.source.MemoDataSource
import camp.nextstep.edu.memo.domain.entity.Memo

object MemoLocalCashingDataSource : MemoDataSource {

    private val memoList = mutableListOf<Memo>()

    override fun getMemoList() = memoList.toList()

    override fun addMemo(memo: Memo) {
        memoList.add(memo)
    }

    override fun deleteMemo(memoId: String) {
        getMemo(memoId)?.let {
            memoList.remove(it)
        }
    }

    override fun getMemo(memoId: String): Memo? {
        return memoList.find { it.id == memoId }
    }

    override fun editMemo(memo: Memo) {
        memoList.forEach {
            if (it.id == memo.id) {
                it.value = memo.value
            }
        }
    }
}