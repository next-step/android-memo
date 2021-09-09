package camp.nextstep.edu.data

import camp.nextstep.edu.domain.Memo

interface MemosSource {

    fun save(memo: Memo)

    fun getAllMemos(): List<Memo>

    fun getMemo(id: String): Memo?

    fun deleteMemo(id: String)
}