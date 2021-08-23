package camp.nextstep.edu.memo.domain.entity

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MemoTest {

    @Test
    fun `같은 값으로 메모를 생성하더라도, 항상 다른 메모 객체가 생성되어야 한다`() {
        // given
        val sameMemoString = "TEST1"
        val oldMemo = Memo.newInstance(sameMemoString)

        // when
        val newMemo = Memo.newInstance(sameMemoString)

        // then
        assertThat(oldMemo).isNotEqualTo(newMemo)
    }
}