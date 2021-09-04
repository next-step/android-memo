package camp.nextstep.edu.memo.data.source.local

import camp.nextstep.edu.memo.data.source.MemoDataSource
import camp.nextstep.edu.memo.domain.entity.Memo

internal object MemoLocalCashingDataSource : MemoDataSource {

    private val memoList = mutableListOf<Memo>()

    override fun getMemoList() = memoList.toList()

    override fun addMemo(memo: Memo) {
        memoList.add(memo)
    }

    override fun deleteMemo(memoId: String) {
        findMemo(memoId)?.let {
            memoList.remove(it)
        }
    }

    override fun findMemo(memoId: String): Memo? {
        return memoList.find { it.id == memoId }
    }

    override fun editMemo(inputMemo: Memo): Memo? {
        memoList.forEach { memo ->
            if (memo.id == inputMemo.id) {
                memo.value = inputMemo.value
                return memo
            }
        }
        return null
    }

}
