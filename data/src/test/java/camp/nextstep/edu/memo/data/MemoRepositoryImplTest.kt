package camp.nextstep.edu.memo.data

import camp.nextstep.edu.memo.domain.entity.Memo
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MemoRepositoryImplTest {

    @Test
    fun `캐싱된 메모가 있을 때, 메모리스트를 불러오면, 데이터를 받아와야한다`() {
        // given : 캐싱된 메모가 있을때,
        val cashedMemos = listOf(Memo("1", "cashing Memo1"), Memo("2", "cashing Memo2"))
        val repository = MemoRepositoryImpl.instance
        cashedMemos.forEach {
            repository.addMemo(it)
        }

        // when : 메모리스트를 불러오면,
        val resultList = repository.getMemoList()

        // then : 데이터를 받아와야한다.
        assertThat(resultList).isEqualTo(cashedMemos)
    }

}