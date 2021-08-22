package camp.nextstep.edu.memo.main

import camp.nextstep.edu.memo.InstantExecutorExtension
import camp.nextstep.edu.memo.MemoEvent
import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository
import camp.nextstep.edu.memo.main.MainViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(value = [InstantExecutorExtension::class])
internal class MainViewModelTest {

    private val memoRepository: MemoRepository = mockk(relaxed = true)
    private lateinit var viewModel: MainViewModel

    @BeforeEach
    fun setup() {
        viewModel = MainViewModel(memoRepository = memoRepository)
    }

    @Test
    fun `작성된 메모를 불러옵니다`() {
        val memoList = listOf(
            Memo("메모1"),
            Memo("메모2")
        )

        every {
            memoRepository.fetch()
        } returns memoList

        viewModel.fetchMemoList()

        assertEquals(viewModel.memoList.value, memoList)
    }

    @Test
    fun `특정 메모를 삭제합니다`() {
        viewModel.delete(position = 0)

        verify {
            memoRepository.delete(position = 0)
        }
        assertEquals(viewModel.memoEvent.value, MemoEvent.Delete(position = 0))
    }
}
