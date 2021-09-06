package camp.nextstep.edu.data.repository

import camp.nextstep.edu.data.local.MemosLocalSource
import camp.nextstep.edu.domain.Memo
import camp.nextstep.edu.domain.MemosSource
import java.util.concurrent.ConcurrentHashMap

/**
 * Created By Malibin
 * on 8월 24, 2021
 */

class MemosRepository internal constructor(
    private val memosLocalSource: MemosSource,
) : MemosSource {
    private val memoCache: MutableMap<String, Memo> = ConcurrentHashMap()

    private var isCacheDirty: Boolean = true

    override fun save(memo: Memo) {
        memoCache[memo.id] = memo
        memosLocalSource.save(memo)
    }

    override fun getAllMemos(): List<Memo> {
        if (isCacheDirty) {
            return memosLocalSource.getAllMemos()
                .also { refreshMemoCache(it) }
        }
        return memoCache.map { it.value }
    }

    private fun refreshMemoCache(memos: List<Memo>) {
        memoCache.clear()
        memos.forEach { memo -> memoCache[memo.id] = memo }
        isCacheDirty = false
    }

    override fun getMemo(id: String): Memo? {
        return memoCache[id] ?: memosLocalSource.getMemo(id)
            ?.also { memoCache[id] = it }
            ?.also { isCacheDirty = true }
    }

    override fun deleteMemo(id: String) {
        memoCache.remove(id)
        memosLocalSource.deleteMemo(id)
    }

    companion object {

        private var instance: MemosRepository? = null
        fun getInstance(): MemosSource = synchronized(this) {
            instance ?: MemosRepository(MemosLocalSource())
                .also { this.instance = it }
        }
    }
}
