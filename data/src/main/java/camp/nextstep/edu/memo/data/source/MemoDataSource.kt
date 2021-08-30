package camp.nextstep.edu.memo.data.source

import camp.nextstep.edu.memo.domain.entity.Memo

interface MemoDataSource {
    fun getMemoList(): List<Memo>
    fun addMemo(memo: Memo)
    fun deleteMemo(memoId: String)
    fun getMemo(memoId: String): Memo?
    fun editMemo(memo: Memo)
}