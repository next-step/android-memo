package camp.nextstep.edu.domain

/**
 * Created By Malibin
 * on 8월 24, 2021
 */

interface MemosSource {

    fun save(memo: Memo)

    fun getAllMemos(): List<Memo>

    fun getMemo(id: String): Memo
}
