package camp.nextstep.edu.memo.delete

import camp.nextstep.edu.memo.MemoEvent
import camp.nextstep.edu.memo.domain.repository.MemoRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class MemoDeleteViewModelTest {

    private val memoRepository: MemoRepository = mockk(relaxed = true)
    private lateinit var viewModel: MemoDeleteViewModel

    @BeforeEach
    fun setup() {
        viewModel = MemoDeleteViewModel(memoRepository = memoRepository)
    }

    @Test
    fun `특정 메모를 삭제합니다`() {
        viewModel.delete(position = 0)

        verify {
            memoRepository.delete(position = 0)
        }
        assertEquals(viewModel.memoEvent.value, MemoEvent.Delete)
    }
}
