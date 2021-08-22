package camp.nextstep.edu.memo.data

import camp.nextstep.edu.memo.domain.entity.Memo
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MemoRepositoryImplTest {

    @Test
    fun `캐싱된 메모가 있을 때, 메모리스트를 불러오면, 데이터를 받아와야한다`() {
        // given
        val cashedMemos = listOf(Memo("1", "cashing Memo1"), Memo("2", "cashing Memo2"))
        val repository = MemoRepositoryImpl.instance

        // when
        cashedMemos.forEach {
            repository.addMemo(it)
        }

        // then
        assertThat(repository.getMemoList()).isEqualTo(cashedMemos)
    }

}