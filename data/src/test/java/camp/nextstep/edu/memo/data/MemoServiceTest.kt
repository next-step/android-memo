package camp.nextstep.edu.memo.data

import camp.nextstep.edu.memo.domain.entity.Memo
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemoServiceTest {

    private lateinit var memoService: MemoService

    @Test
    fun `작성한 메모를 저장합니다`() {
        memoService = MemoService.getInstance()
        val memo = Memo("메모 작성")

        memoService.save(memo)

        val actual = memoService
            .fetch()
            .first()

        assertThat(actual).isEqualTo(memo)
    }

    @Test
    fun `작성된 메모들을 불러옵니다`() {
        memoService = mockk()
        val memoList = listOf(
            Memo("메모1"),
            Memo("메모2")
        )

        every {
            memoService.fetch()
        } returns memoList

        assertThat(memoService.fetch()).isEqualTo(memoList)
    }
}
