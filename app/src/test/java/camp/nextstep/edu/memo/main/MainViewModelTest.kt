package camp.nextstep.edu.memo.main

import camp.nextstep.edu.memo.InstantExecutorExtension
import camp.nextstep.edu.memo.MemoEvent
import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository
import camp.nextstep.edu.memo.main.MainViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID
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
            Memo(value = "메모1"),
            Memo(value = "메모2")
        )

        every {
            memoRepository.fetch()
        } returns memoList

        viewModel.fetchMemoList()

        assertEquals(viewModel.memoList.value, memoList)
    }

    @Test
    fun `특정 메모를 삭제합니다`() {
        viewModel.delete(uuid = ID)

        verify {
            memoRepository.delete(uuid = ID)
        }
        assertEquals(viewModel.memoEvent.value, MemoEvent.Delete(uuid = ID))
    }

    companion object {
        private val ID = UUID.randomUUID()
    }
}
