package camp.nextstep.edu.memo.data

import camp.nextstep.edu.memo.domain.MemoRepository
import camp.nextstep.edu.memo.domain.entity.Memo

class MemoRepositoryImpl(
    private val service: MemoService
) : MemoRepository {

    override fun save(memo: Memo) {
        service.save(memo)
    }

    override fun fetch(): List<Memo> = service.fetch()
}
