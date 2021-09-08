package camp.nextstep.edu.data

import camp.nextstep.edu.domain.MemoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal interface DataModule {
    @Binds
    fun bindMemoRepository(impl: MemoRepositoryImpl): MemoRepository
}
