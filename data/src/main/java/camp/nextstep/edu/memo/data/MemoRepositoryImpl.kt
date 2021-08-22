package camp.nextstep.edu.memo.data

import camp.nextstep.edu.memo.data.source.MemoDataSource
import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository

class MemoRepositoryImpl(
    private val dataSource: MemoDataSource
) : MemoRepository {
    override fun getMemoList(): List<Memo> {
        return dataSource.getMemoList()
    }

    override fun addMemo(memo: Memo) {
        dataSource.addMemo(memo)
    }
}