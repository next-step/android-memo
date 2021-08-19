package camp.nextstep.edu.memo.data

import camp.nextstep.edu.memo.domain.entity.Memo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemoServiceTest {

    private val memoService = MemoService()

    @Test
    fun `작성한 메모를 저장합니다`() {
        val memo = Memo("메모 작성")
        memoService.save(memo)

        assertThat(memoService.memoList.first()).isEqualTo(memo)
    }
}
