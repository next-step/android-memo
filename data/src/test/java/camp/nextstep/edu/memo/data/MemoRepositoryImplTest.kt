package camp.nextstep.edu.memo.data

import camp.nextstep.edu.memo.domain.repository.MemoRepository
import camp.nextstep.edu.memo.domain.entity.Memo
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class MemoRepositoryImplTest {

    private val memoService: MemoService = mockk(relaxed = true)
    private lateinit var repository: MemoRepository

    @BeforeEach
    fun setup() {
        repository = MemoRepositoryImpl(service = memoService)
    }

    @Test
    fun `작성한 메모를 저장합니다`() {
        val memo = Memo("메모 작성")

        repository.save(memo)

        verify {
            memoService.save(memo)
        }
    }

    @Test
    fun `작성된 메모들을 불러옵니다`() {
        val memoList = listOf(
            Memo("메모1"),
            Memo("메모2")
        )

        every {
            memoService.fetch()
        } returns memoList

        val actual = repository.fetch()

        Assertions.assertThat(actual).isEqualTo(memoList)
    }

}
