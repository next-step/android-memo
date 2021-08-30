package camp.nextstep.edu.memo.edit

import camp.nextstep.edu.domain.Memo
import camp.nextstep.edu.domain.MemosSource
import camp.nextstep.edu.memo.R
import camp.nextstep.edu.memo.utils.InstantTaskExecutorExtension
import camp.nextstep.edu.memo.utils.takeValue
import com.google.common.truth.Truth.assertThat
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.RegisterExtension

/**
 * Created By Malibin
 * on 8월 26, 2021
 */

class EditMemoViewModelTest {

    companion object {
        @JvmField
        @RegisterExtension
        val instantTaskExecutorExtension = InstantTaskExecutorExtension()
    }

    lateinit var editMemoViewModel: EditMemoViewModel
    lateinit var memosRepository: MemosSource

    @BeforeEach
    fun setUp() {
        memosRepository = mockk(relaxed = true)
        editMemoViewModel = EditMemoViewModel(memosRepository)
    }

    // LiveData의 onChanged가 호출되었는 지 테스트는 어떻게 하면 좋을 지 잘 모르겠습니다.
    // memoSaved LiveData가 업데이트 되었는지 또는 되지 않았다는 점을 테스트 하고싶은데,
    // 이 방법 보다는 그냥 값을 넣어서 해당 업데이트된 값으로 테스트하는 게 더 나을까요?
    @Test
    fun `메모의 제목과 내용을 채우면 해당 내용으로 작성 완료할 수 있다`() {
        // given
        val memoCaptureSlot = slot<Memo>()
        every { memosRepository.save(capture(memoCaptureSlot)) } just Runs

        // when
        editMemoViewModel.title.value = "title"
        editMemoViewModel.content.value = "content"

        editMemoViewModel.saveMemo()

        // then
        val actualMemo = memoCaptureSlot.captured

        assertAll(
            { verify(exactly = 1) { memosRepository.save(any()) } },
            { assertThat(actualMemo.title).isEqualTo("title") },
            { assertThat(actualMemo.content).isEqualTo("content") },
            { assertThat(editMemoViewModel.memoSaved.takeValue()).isNull() },
        )
    }

    @Test
    fun `메모의 제목이 null 이거나 공백이면 저장할 수 없고, 에러 메시지 이벤트를 발생시킨다`() {
        // when
        editMemoViewModel.content.value = "content"
        editMemoViewModel.saveMemo()

        // then
        assertAll(
            { verify(exactly = 0) { memosRepository.save(any()) } },
            { assertThat(editMemoViewModel.toastMessage.takeValue()).isEqualTo(R.string.fill_memo_title) },
        )
    }
}
