package camp.nextstep.edu.memo.data

import camp.nextstep.edu.memo.domain.entity.Memo
import java.util.UUID

internal interface MemoService {

    fun save(memo: Memo)

    fun fetch(): List<Memo>

    fun update(memo: Memo)

    fun delete(uuid: UUID)

}
