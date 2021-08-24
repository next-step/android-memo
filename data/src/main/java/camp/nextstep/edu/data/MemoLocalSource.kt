package camp.nextstep.edu.data

import camp.nextstep.edu.domain.Memo
import camp.nextstep.edu.domain.MemoSource

class MemoLocalSource(
    initialMemos: List<Memo> = emptyList()
) : MemoSource {
    private val memory: MutableList<Memo> = initialMemos.toMutableList()

    override fun save(memo: Memo) {
        memory.add(memo)
    }

    override fun getAllMemos(): List<Memo> {
        return memory.toList()
    }
}
