package camp.nextstep.edu.memo.data

import camp.nextstep.edu.memo.domain.repository.MemoRepository

object Injector {

    @Volatile
    private var memoRepositoryInstance: MemoRepository? = null

    fun providesMemoRepository(): MemoRepository = memoRepositoryInstance
        ?: MemoRepositoryImpl.getInstance()
}
