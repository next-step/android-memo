package camp.nextstep.edu.memo.domain.repository

import camp.nextstep.edu.memo.domain.entity.Memo

interface MemoRepository {

    fun save(memo: Memo)

    fun fetch(): List<Memo>

    fun update(position: Int, memo: Memo)
}
