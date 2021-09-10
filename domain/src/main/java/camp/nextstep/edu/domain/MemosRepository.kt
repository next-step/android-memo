package camp.nextstep.edu.domain

/**
 * Created By Malibin
 * on 8월 24, 2021
 */

interface MemosRepository {

    fun save(memo: Memo)

    fun getAllMemos(): List<Memo>

    fun getMemo(id: String): Memo?

    fun deleteMemo(id: String)
}
