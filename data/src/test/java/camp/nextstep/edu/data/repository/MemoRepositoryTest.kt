package camp.nextstep.edu.data.repository

import camp.nextstep.edu.domain.Memo
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

/**
 * Created By Malibin
 * on 8월 24, 2021
 */

class MemoRepositoryTest {

    @Test
    fun `메모를 저장하고 꺼내올 수 있다`() {
        // given
        val memoRepository = MemoRepository.getInstance()
        val memo = Memo(
            title = "title",
            content = "content",
            id = "1"
        )

        // when
        memoRepository.save(memo)
        val retrieveMemos = memoRepository.getAllMemos()

        // then
        assertAll(
            { assertThat(retrieveMemos.size).isEqualTo(1) },
            { assertThat(retrieveMemos).isEqualTo(listOf(memo)) },
        )
    }

    @Test
    fun `메모 저장소는 싱글턴 객체이다`() {
        // given
        val memoRepository1 = MemoRepository.getInstance()
        val memoRepository2 = MemoRepository.getInstance()

        // then
        assertThat(memoRepository1).isEqualTo(memoRepository2)
    }
}
