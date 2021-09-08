package camp.nextstep.edu.data

import camp.nextstep.edu.domain.MemoRepository
import javax.inject.Inject

internal class MemoRepositoryImpl @Inject constructor() : MemoRepository {
    override fun findMemos(): List<String> {
        return emptyList()
    }
}
