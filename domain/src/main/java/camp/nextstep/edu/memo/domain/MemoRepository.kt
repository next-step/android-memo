package camp.nextstep.edu.memo.domain

import camp.nextstep.edu.memo.domain.entity.Memo

interface MemoRepository {

    fun save(memo: Memo)

    fun fetch(): List<Memo>
}
