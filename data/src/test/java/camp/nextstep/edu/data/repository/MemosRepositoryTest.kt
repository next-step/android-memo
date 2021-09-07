package camp.nextstep.edu.data.repository

import camp.nextstep.edu.data.local.MemosLocalSource
import camp.nextstep.edu.domain.Memo
import camp.nextstep.edu.domain.MemosSource
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

/**
 * Created By Malibin
 * on 8월 24, 2021
 */

class MemosRepositoryTest {
    private lateinit var memosRepository: MemosSource
    private lateinit var memosLocalSource: MemosSource

    @BeforeEach
    fun setUp() {
        memosLocalSource = mockk(relaxed = true)
        memosRepository = MemosRepository(memosLocalSource)
    }

    @Test
    fun `메모를 저장하고 꺼내올 수 있다`() {
        // given
        val memoRepository = MemosRepository(MemosLocalSource())
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
    fun `모든 메모를 두 번 불러오면 두 번째는 캐시된 메모를 불러온다`() {
        // given
        val localMemos = listOf(Memo("title", "content", "1"))
        every { memosLocalSource.getAllMemos() } returns localMemos

        // when
        memosRepository.getAllMemos()
        val twiceRetrievedMemos = memosRepository.getAllMemos()

        // then
        assertAll(
            { verify(exactly = 1) { memosLocalSource.getAllMemos() } },
            { assertThat(twiceRetrievedMemos).containsExactlyElementsIn(localMemos) },
        )
    }

    @Test
    fun `처음 불러오는 모든 메모는 반드시 캐시가 아닌 Source 객체에서 가져온다`() {
        // when
        memosRepository.getAllMemos()

        // then
        verify(exactly = 1) { memosLocalSource.getAllMemos() }
    }

    @Test
    fun `초기 상태일 때, 메모를 저장하고 모든 메모를 불러오면 캐시된 메모가 아닌 source 객체에서 불러온다`() {
        // when
        memosRepository.save(Memo("title", "content"))
        memosRepository.getAllMemos()

        // then
        verify(exactly = 1) { memosLocalSource.getAllMemos() }
    }

    @Test
    fun `특정 id의 메모를 불러올 수 있다`() {
        // given
        val memoRepository = MemosRepository(MemosLocalSource())
        val memo = Memo(
            title = "title",
            content = "content",
            id = "1"
        )

        // when
        memoRepository.save(memo)
        val retrieveMemo = memoRepository.getMemo("1")

        // then
        assertThat(retrieveMemo).isEqualTo(memo)
    }

    @Test
    fun `저장되어 있지 않은 메모 id로 메모를 가져오면 null을 반환한다`() {
        // given
        val memoRepository = MemosRepository(MemosLocalSource())

        // when
        val actualMemo = memoRepository.getMemo("1")

        // then
        assertThat(actualMemo).isNull()
    }

    @Test
    fun `메모를 저장하고 해당 메모를 가져오면, 캐시로부터 불러온다`() {
        // given
        val memo = Memo("title", "content", "1")

        // when
        memosRepository.save(memo)
        val retrieveMemo = memosRepository.getMemo("1")

        // then
        assertAll(
            { assertThat(memo).isEqualTo(retrieveMemo) },
            { verify(exactly = 0) { memosLocalSource.getMemo(any()) } },
        )
    }

    @Test
    fun `특정 메모를 캐시에서 가져오지 못했다면, 다음 모든 메모는 source에서 가져온다`() {
        // when
        memosRepository.getMemo("1")

        // then
        verify(exactly = 1) { memosLocalSource.getMemo(any()) }

        // when
        memosRepository.getAllMemos()

        // then
        verify(exactly = 1) { memosLocalSource.getAllMemos() }
    }

    @Test
    fun `메모를 삭제할 수 있다`() {
        // given
        val memo = Memo(
            title = "title",
            content = "content",
            id = "1"
        )
        val memoRepository = MemosRepository(MemosLocalSource(listOf(memo)))

        // when
        memoRepository.deleteMemo(memo.id)
        val retrieveMemo = memoRepository.getMemo("1")

        // then
        assertThat(retrieveMemo).isNull()
    }
}
