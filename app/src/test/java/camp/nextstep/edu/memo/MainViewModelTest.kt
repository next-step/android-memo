package camp.nextstep.edu.memo

import camp.nextstep.edu.domain.Memo
import camp.nextstep.edu.domain.MemosSource
import camp.nextstep.edu.memo.utils.InstantTaskExecutorExtension
import camp.nextstep.edu.memo.utils.takeValue
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

/**
 * Created By Malibin
 * on 8월 26, 2021
 */

class MainViewModelTest {

    companion object {
        @JvmField
        @RegisterExtension
        val instantTaskExecutorExtension = InstantTaskExecutorExtension()
    }

    lateinit var mainViewModel: MainViewModel
    lateinit var memosRepository: MemosSource

    @BeforeEach
    fun setUp() {
        memosRepository = mockk(relaxed = true)
        mainViewModel = MainViewModel(memosRepository)
    }

    @Test
    fun `모든 메모를 불러온다`() {
        // given
        val expectedMemos = listOf(
            Memo("title1", "content1"),
            Memo("title2", "content2"),
        )
        every { memosRepository.getAllMemos() } returns expectedMemos

        // when
        mainViewModel.loadAllMemos()

        // then
        val actualMemos = mainViewModel.memos.takeValue()
        assertThat(actualMemos).containsExactlyElementsIn(expectedMemos).inOrder()
    }

    @Test
    fun `메모를 삭제할 수 있다`() {
        // given
        val memos = listOf(
            Memo("title1", "content1", "id"),
        )
        every { memosRepository.getAllMemos() } returns memos
        mainViewModel.loadAllMemos()

        // when
        mainViewModel.deleteMemo("id")

        // then
        assertThat(mainViewModel.memos.takeValue()).isEmpty()
    }
}
