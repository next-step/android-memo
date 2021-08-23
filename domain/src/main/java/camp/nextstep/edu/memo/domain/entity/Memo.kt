package camp.nextstep.edu.memo.domain.entity

import java.util.UUID

data class Memo(
    val uuid: UUID = UUID.randomUUID(),
    val value: String
)
