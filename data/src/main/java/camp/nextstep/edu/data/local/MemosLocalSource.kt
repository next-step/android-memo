package camp.nextstep.edu.data.local

import camp.nextstep.edu.data.MemosSource
import camp.nextstep.edu.domain.Memo

internal class MemosLocalSource(
    initialMemos: List<Memo> = emptyList()
) : MemosSource {
    private val memory: MutableList<Memo> = initialMemos.toMutableList()

    override fun save(memo: Memo) {
        memory.add(memo)
    }

    override fun getAllMemos(): List<Memo> {
        return memory.toList()
    }

    override fun getMemo(id: String): Memo? {
        return memory.find { it.id == id }
    }

    override fun deleteMemo(id: String) {
        val memo = memory.find { it.id == id } ?: return
        memory.remove(memo)
    }
}
