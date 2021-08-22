package camp.nextstep.edu.memo.domain.entity

import java.util.*

data class Memo(
    val id: String,
    val value: String,
) {
    companion object {
        fun newInstance(memo: String) = Memo(id = UUID.randomUUID().toString(), value = memo)
    }
}