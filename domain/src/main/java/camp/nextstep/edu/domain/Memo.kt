package camp.nextstep.edu.domain

import java.util.*

/**
 * Created By Malibin
 * on 8월 23, 2021
 */

data class Memo(
    val title: String,
    val content: String,
    val id: String = UUID.randomUUID().toString(),
)
