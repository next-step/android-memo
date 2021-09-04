package camp.nextstep.edu.memo.data.di

import camp.nextstep.edu.memo.data.MemoRepositoryImpl
import camp.nextstep.edu.memo.domain.repository.MemoRepository

object DataModuleInjector {
    fun provideMemoRepository(): MemoRepository = MemoRepositoryImpl.instance
}