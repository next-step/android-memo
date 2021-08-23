package camp.nextstep.edu.memo.domain.repository

import camp.nextstep.edu.memo.domain.entity.Memo
import java.util.UUID

interface MemoRepository {

    fun save(memo: Memo)

    fun fetch(): List<Memo>

    fun update(memo: Memo)

    fun delete(uuid: UUID)
}
