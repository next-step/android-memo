package camp.nextstep.edu.data.repository

import camp.nextstep.edu.data.local.MemoLocalSource
import camp.nextstep.edu.domain.Memo
import camp.nextstep.edu.domain.MemoSource

/**
 * Created By Malibin
 * on 8월 24, 2021
 */

class MemoRepository internal constructor(
    private val memoLocalSource: MemoSource,
) : MemoSource {

    override fun save(memo: Memo) {
        memoLocalSource.save(memo)
    }

    override fun getAllMemos(): List<Memo> {
        return memoLocalSource.getAllMemos()
    }

    companion object {
        private var instance: MemoRepository? = null

        fun getInstance(): MemoSource = synchronized(this) {
            instance ?: MemoRepository(MemoLocalSource())
                .also { this.instance = it }
        }
    }
}
