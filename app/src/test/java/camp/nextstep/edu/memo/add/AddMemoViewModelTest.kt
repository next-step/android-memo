package camp.nextstep.edu.memo.add

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

class AddMemoViewModelTest {
    private lateinit var viewModel: AddMemoViewModel
    private lateinit var memoRepository: MemoRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        memoRepository = mockk()
        viewModel = AddMemoViewModel(memoRepository)
    }

    @Test
    fun `추가 버튼을 누르면, 메모가 추가 되어야 한다`() {
        // given
        val inputText = "TestText"
        val expressionSlot = slot<Memo>()
        every { memoRepository.addMemo(capture(expressionSlot)) } answers { nothing }

        // when
        viewModel.memo.value = inputText
        viewModel.addMemo()

        // then
        val actual = expressionSlot.captured
        assertThat(actual.value).isEqualTo(inputText)
        verify { memoRepository.addMemo(actual) }
    }


    @Test
    fun `추가 버튼을 누르면, 완료가 되어야한다`() {
        // given
        every { memoRepository.addMemo(any()) } answers { nothing }

        // when
        viewModel.addMemo()

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