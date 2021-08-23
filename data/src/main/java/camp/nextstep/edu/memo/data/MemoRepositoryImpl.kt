package camp.nextstep.edu.memo.data

import camp.nextstep.edu.memo.domain.repository.MemoRepository
import camp.nextstep.edu.memo.domain.entity.Memo
import java.util.UUID

class MemoRepositoryImpl(
    private val service: MemoService
) : MemoRepository {

    override fun save(memo: Memo) {
        service.save(memo)
    }

    override fun fetch(): List<Memo> = service.fetch()

    override fun update(memo: Memo) {
        service.update(memo = memo)
    }

    override fun delete(uuid: UUID) {
        service.delete(uuid)
    }

    companion object {
        @Volatile
        private var instance: MemoRepository? = null

        fun getInstance(): MemoRepository = instance ?: synchronized(this) {
            instance ?: MemoRepositoryImpl(service = MemoService.getInstance()).also {
                instance = it
            }
        }
    }
}
