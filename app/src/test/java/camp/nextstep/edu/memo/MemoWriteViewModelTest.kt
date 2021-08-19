package camp.nextstep.edu.memo

import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class MemoWriteViewModelTest {

    private val memoRepository: MemoRepository = mockk(relaxed = true)
    private lateinit var viewModel: MemoWriteViewModel

    @BeforeEach
    fun setup() {
        viewModel = MemoWriteViewModel(memoRepository = memoRepository)
    }

    @Test
    fun `작성한 메모를 저장합니다`() {
        val memoValue = "메모1"

        viewModel.saveMemo(memo = memoValue)

        verify {
            memoRepository.save(memo = Memo(memoValue))
        }
    }

}
