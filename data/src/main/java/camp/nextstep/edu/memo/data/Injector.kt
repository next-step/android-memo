package camp.nextstep.edu.memo.data

import camp.nextstep.edu.memo.domain.repository.MemoRepository

object Injector {

    fun providesMemoRepository(): MemoRepository = MemoRepositoryImpl.getInstance()
}
