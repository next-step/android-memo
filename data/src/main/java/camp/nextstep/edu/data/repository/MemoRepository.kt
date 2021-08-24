package camp.nextstep.edu.data.repository

import camp.nextstep.edu.data.local.MemoLocalSource
import camp.nextstep.edu.domain.Memo
import camp.nextstep.edu.domain.MemoSource
import java.util.concurrent.ConcurrentHashMap

/**
 * Created By Malibin
 * on 8월 24, 2021
 */

class MemoRepository internal constructor(
    private val memoLocalSource: MemoSource,
) : MemoSource {
    private val memoCache: MutableMap<String, Memo> = ConcurrentHashMap()

    private var isCacheDirty: Boolean = true

    override fun save(memo: Memo) {
        memoCache[memo.id] = memo
        memoLocalSource.save(memo)
    }

    override fun getAllMemos(): List<Memo> {
        if (isCacheDirty) {
            return memoLocalSource.getAllMemos()
                .also { refreshMemoCache(it) }
        }
        return memoCache.map { it.value }
    }

    private fun refreshMemoCache(memos: List<Memo>) {
        memoCache.clear()
        memos.forEach { memo -> memoCache[memo.id] = memo }
        isCacheDirty = false
    }

    override fun getMemo(id: String): Memo {
        return memoCache[id] ?: memoLocalSource.getMemo(id)
            .also { isCacheDirty = true }
    }

    companion object {
        private var instance: MemoRepository? = null

        fun getInstance(): MemoSource = synchronized(this) {
            instance ?: MemoRepository(MemoLocalSource())
                .also { this.instance = it }
        }
    }
}
