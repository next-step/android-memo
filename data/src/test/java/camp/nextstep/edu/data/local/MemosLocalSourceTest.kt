package camp.nextstep.edu.data.local

import camp.nextstep.edu.domain.Memo
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

/**
 * Created By Malibin
 * on 8월 23, 2021
 */

class MemosLocalSourceTest {

    @Test
    fun `메모를 저장하고 꺼내올 수 있다`() {
        // given
        val memoLocalSource = MemosLocalSource()
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
        val memoLocalSource = MemosLocalSource()
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
    fun `저장되어 있지 않은 메모 id로 메모를 불러오면 null이 반환된다`() {
        // given
        val memoLocalSource = MemosLocalSource()

        // when
        val actualMemo = memoLocalSource.getMemo("1")

        // then
        assertThat(actualMemo).isNull()
    }

    @Test
    fun `메모를 삭제할 수 있다`() {
        // given
        val memo = Memo(
            title = "title",
            content = "content",
            id = "1"
        )
        val memoLocalSource = MemosLocalSource(listOf(memo))

        // when
        memoLocalSource.deleteMemo(memo.id)
        val retrieveMemo = memoLocalSource.getMemo("1")

        // then
        assertThat(retrieveMemo).isNull()
    }
}
