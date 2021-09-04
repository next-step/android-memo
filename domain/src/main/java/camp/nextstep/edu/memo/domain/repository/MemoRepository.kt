package camp.nextstep.edu.memo.domain.repository

import camp.nextstep.edu.memo.domain.entity.Memo

interface MemoRepository {
    fun getMemoList(): List<Memo>
    fun addMemo(memo: Memo)
    fun deleteMemo(memoId: String)
    fun findMemo(memoId: String): Memo?
    fun editMemo(memo: Memo): Result<Memo>
}
