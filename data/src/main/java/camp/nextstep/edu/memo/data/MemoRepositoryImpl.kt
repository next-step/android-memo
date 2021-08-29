package camp.nextstep.edu.memo.data

import camp.nextstep.edu.memo.data.source.MemoDataSource
import camp.nextstep.edu.memo.data.source.local.MemoLocalCashingDataSource
import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository

class MemoRepositoryImpl(
    private val dataSource: MemoDataSource = MemoLocalCashingDataSource
) : MemoRepository {
    override fun getMemoList(): List<Memo> {
        return dataSource.getMemoList()
    }

    override fun addMemo(memo: Memo) {
        dataSource.addMemo(memo)
    }

    override fun deleteMemo(memoId: String) {
        dataSource.deleteMemo(memoId)
    }

    override fun getMemo(memoId: String): Memo? {
        return dataSource.getMemo(memoId)
    }

    override fun editMemo(memo: Memo) {
        dataSource.editMemo(memo)
    }

    companion object {
        val instance = MemoRepositoryImpl()
    }
}
