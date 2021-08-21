package camp.nextstep.edu.memo.write

import camp.nextstep.edu.memo.MemoEvent
import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
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

    @Test
    fun `빈 메모를 작성하고 저장 버튼을 누르면 메모가 저장되지 않습니다`() {
        val memoValue = ""

        viewModel.saveMemo(memo = memoValue)

        assertEquals(viewModel.memoEvent.value, MemoEvent.Cancel)
    }

    @Test
    fun `취소 버튼을 누릅니다`() {
        viewModel.cancel()

        assertEquals(viewModel.memoEvent.value, MemoEvent.Cancel)
    }

}
