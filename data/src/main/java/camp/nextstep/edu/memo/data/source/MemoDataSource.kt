package camp.nextstep.edu.memo.data.source

import camp.nextstep.edu.memo.domain.entity.Memo

interface MemoDataSource {
    fun getMemoList(): List<Memo>
    fun addMemo(memo: Memo)
}