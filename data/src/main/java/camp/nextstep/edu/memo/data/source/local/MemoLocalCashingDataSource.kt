package camp.nextstep.edu.memo.data.source.local

import camp.nextstep.edu.memo.data.source.MemoDataSource
import camp.nextstep.edu.memo.domain.entity.Memo

object MemoLocalCashingDataSource : MemoDataSource {

    private val memoList = mutableListOf<Memo>()

    override fun getMemoList() = memoList.toList()

    override fun addMemo(memo: Memo) {
        memoList.add(memo)
    }
}