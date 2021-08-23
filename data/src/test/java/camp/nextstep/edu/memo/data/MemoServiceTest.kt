package camp.nextstep.edu.memo.data

import camp.nextstep.edu.memo.domain.entity.Memo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.UUID

internal class MemoServiceTest {

    private lateinit var memoService: MemoService

    @BeforeEach
    fun setup() {
        memoService = MemoService()
    }

    @Test
    fun `작성한 메모를 저장합니다`() {
        val memo = Memo(uuid = ID, value = "메모 작성")

        memoService.save(memo)

        val actual = memoService
            .fetch()
            .first()

        assertThat(actual).isEqualTo(memo)
    }

    @Test
    fun `작성된 메모들을 불러옵니다`() {
        val memoList = listOf(
            Memo(value = "메모1"),
            Memo(value = "메모2")
        )
        memoList.forEach(memoService::save)

        val actual = memoService.fetch()

        assertThat(actual).containsAll(memoList)
    }

    @Test
    fun `특정 메모를 수정합니다`() {
        `작성한 메모를 저장합니다`()

        val updatedMemo = Memo(uuid = ID, value = "메모 수정")
        memoService.update(memo = updatedMemo)

        val actual = memoService
            .fetch()
            .first()

        assertThat(actual).isEqualTo(updatedMemo)
    }

    @Test
    fun `특정 메모를 삭제합니다`() {
        `작성한 메모를 저장합니다`()

        memoService.delete(uuid = ID)

        val actual = memoService.fetch()

        assertThat(actual).isEqualTo(emptyList<Memo>())
    }

    companion object {
        private val ID = UUID.randomUUID()
    }
}
