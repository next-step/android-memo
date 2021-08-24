package camp.nextstep.edu.data.local

import camp.nextstep.edu.domain.Memo
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

/**
 * Created By Malibin
 * on 8월 23, 2021
 */

class MemoLocalSourceTest {

    @Test
    fun `메모를 저장하고 꺼내올 수 있다`() {
        // given
        val memoLocalSource = MemoLocalSource()
        val memo = Memo(
            title = "title",
            content = "content",
            id = "1"
        )

        // when
        memoLocalSource.save(memo)
        val retrieveMemos = memoLocalSource.getAllMemos()

        // then
        assertAll(
            { assertThat(retrieveMemos.size).isEqualTo(1) },
            { assertThat(retrieveMemos).isEqualTo(listOf(memo)) },
        )
    }
}
