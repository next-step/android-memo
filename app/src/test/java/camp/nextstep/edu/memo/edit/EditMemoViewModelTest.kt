package camp.nextstep.edu.memo.edit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository
import camp.nextstep.edu.memo.util.MemoEvent
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EditMemoViewModelTest {
    private lateinit var viewModel: EditMemoViewModel
    private lateinit var memoRepository: MemoRepository

    private val memoId = "1"
    private val expectedMemo = Memo(memoId, "test")

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        memoRepository = mockk()
        every { memoRepository.findMemo(memoId) } answers { expectedMemo }

        viewModel = EditMemoViewModel(
            memoId = memoId,
            memoRepository = memoRepository
        )
    }

    @Test
    fun `메모ID에 해당하는 메모 데이터를 정상적으로 받아와야한다`() {
        // when
        viewModel = EditMemoViewModel(
            memoId = memoId,
            memoRepository = memoRepository
        )

        // then
        assertThat(viewModel.memo.value).isEqualTo(expectedMemo.value)
    }

    @Test
    fun `변경 버튼을 누르면, 메모가 변경 되어야한다`() {
        // given
        val editText = "editTestText"
        val expressionSlot = slot<Memo>()
        every { memoRepository.editMemo(capture(expressionSlot)) } answers {
            Result.success(Memo.newInstance(""))
        }

        // when
        viewModel.memo.value = editText
        viewModel.editMemo()

        // then
        val actual = expressionSlot.captured
        assertThat(actual.value).isEqualTo(editText)
        verify { memoRepository.editMemo(actual) }
    }

    @Test
    fun `변경 버튼을 누르면, 메모가 완료 되어야한다`() {
        // given
        every { memoRepository.editMemo(expectedMemo) } answers { Result.success(Memo.newInstance("")) }

        // when
        viewModel.editMemo()

        // then
        assertThat(viewModel.actionEvent.value?.peekContent()).isEqualTo(MemoEvent.Complete)
    }


    @Test
    fun `취소 버튼를 누르면, 취소가 되어야한다`() {
        // when
        viewModel.cancel()

        // then
        assertThat(viewModel.actionEvent.value?.peekContent()).isEqualTo(MemoEvent.Cancel)
    }

}