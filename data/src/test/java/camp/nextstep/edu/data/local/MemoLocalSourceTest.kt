package camp.nextstep.edu.data.local

import camp.nextstep.edu.domain.Memo
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

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

    @Test
    fun `특정 id의 메모를 불러올 수 있다`() {
        // given
        val memoLocalSource = MemoLocalSource()
        val memo = Memo(
            title = "title",
            content = "content",
            id = "1"
        )

        // when
        memoLocalSource.save(memo)
        val retrieveMemo = memoLocalSource.getMemo("1")

        // then
        assertThat(retrieveMemo).isEqualTo(memo)
    }

    @Test
    fun `저장되어 있지 않은 메모 id로는 메모를 가져올 수 없다`() {
        // given
        val memoLocalSource = MemoLocalSource()

        // when
        val exception = assertThrows<IllegalArgumentException> { memoLocalSource.getMemo("1") }

        // then
        assertThat(exception).hasMessageThat().contains("cannot find memo of id : 1")
    }
}
