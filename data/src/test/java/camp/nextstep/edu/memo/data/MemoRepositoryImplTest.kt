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

    @Test
    fun `삭제할 메모가 있을 때, 메모Id를 통해 삭제하면, 메모가 삭제되어야한다`() {
        // given : 삭제할 메모가 있을때,
        val targetMemo = Memo("1", "cashing Memo1")
        val cashedMemos = listOf(targetMemo, Memo("2", "cashing Memo2"))
        val repository = MemoRepositoryImpl.instance
        cashedMemos.forEach {
            repository.addMemo(it)
        }
        // when : 메모를 삭제하면,
        repository.deleteMemo(targetMemo.id)
        val resultList = repository.getMemoList()

        // then : 메모가 삭제되어야한다.
        assertThat(resultList).doesNotContain(targetMemo)
    }

    @Test
    fun `검색하려는 메모가 있을때, 메모Id를 검색하면, 해당하는 메모를 정상적으로 받아온다`() {
        // given
        val targetMemo = Memo("1", "cashing Memo1")
        val cashedMemos = listOf(targetMemo, Memo("2", "cashing Memo2"))
        val repository = MemoRepositoryImpl.instance
        cashedMemos.forEach {
            repository.addMemo(it)
        }
        // when
        val result = repository.findMemo(targetMemo.id)

        // then
        assertThat(result).isEqualTo(targetMemo)
    }

    @Test
    fun `기존 메모가 있을때, 메모를 수정하면, 메모가 수정되어야한다`() {
        // given : 기존 메모가 있을때
        val beforeMemo = "before changed Memo"
        val editedMemo = "edited Memo"
        val targetMemo = Memo("1", beforeMemo)
        val cashedMemos = listOf(targetMemo, Memo("2", "cashing Memo2"))
        val repository = MemoRepositoryImpl.instance
        cashedMemos.forEach {
            repository.addMemo(it)
        }
        // when : 메모를 수정하면
        val result = repository.editMemo(Memo(targetMemo.id, editedMemo))

        // then : 메모가 수정되어야한다.
        assertThat(result.isSuccess).isEqualTo(true)
        assertThat(result.getOrNull()?.value).isEqualTo(editedMemo)
    }

    @Test
    fun `기존 메모가 없을때, 메모를 수정하면, 실패해야한다`() {
        // given : 기존 메모가 있을때
        val beforeMemo = "before changed Memo"
        val editedMemo = "edited Memo"
        val targetMemo = Memo("1", beforeMemo)
        val cashedMemos = listOf(Memo("2", "cashing Memo2"))
        val repository = MemoRepositoryImpl.instance
        cashedMemos.forEach {
            repository.addMemo(it)
        }
        // when : 메모를 수정하면
        val result = repository.editMemo(Memo(targetMemo.id, editedMemo))

        // then : 실패한 결과가 나와야한다.
        assertThat(result.isFailure).isEqualTo(true)
        assertThat(result.exceptionOrNull()).isNotNull()
    }

}