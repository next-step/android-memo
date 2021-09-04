package camp.nextstep.edu.memo.data

import camp.nextstep.edu.memo.data.source.MemoDataSource
import camp.nextstep.edu.memo.data.source.local.MemoLocalCashingDataSource
import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository

internal class MemoRepositoryImpl(
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

    override fun findMemo(memoId: String): Memo? {
        return dataSource.findMemo(memoId)
    }

    override fun editMemo(memo: Memo): Result<Memo> =
        if (dataSource.editMemo(memo) == null) {
            Result.failure(Exception("변경실패"))
        } else {
            Result.success(memo)
        }


    companion object {
        val instance = MemoRepositoryImpl()
    }
}
