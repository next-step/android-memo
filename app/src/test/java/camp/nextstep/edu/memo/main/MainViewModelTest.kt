package camp.nextstep.edu.memo.main

import camp.nextstep.edu.memo.domain.entity.Memo
import camp.nextstep.edu.memo.domain.repository.MemoRepository
import camp.nextstep.edu.memo.main.MainViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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
}
