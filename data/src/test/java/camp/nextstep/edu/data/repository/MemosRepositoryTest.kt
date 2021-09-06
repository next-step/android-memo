package camp.nextstep.edu.data.repository

import camp.nextstep.edu.data.local.MemosLocalSource
import camp.nextstep.edu.domain.Memo
import camp.nextstep.edu.domain.MemosSource
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

/**
 * Created By Malibin
 * on 8월 24, 2021
 */

class MemosRepositoryTest {

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
    fun `메모 저장소는 싱글턴 객체이다`() {
        // given
        val memoRepository1 = MemosRepository.getInstance()
        val memoRepository2 = MemosRepository.getInstance()

        // then
        assertThat(memoRepository1).isEqualTo(memoRepository2)
    }

    @Test
    fun `모든 메모를 두 번 불러오면 두 번째는 캐시된 메모를 불러온다`() {
        // given
        var getAllMemoCallCount = 0
        val localMemos = listOf(Memo("title", "content", "1"))
        val memoRepository = MemosRepository(object : MemosSource {
            override fun save(memo: Memo) {
                /*Nothing*/
            }

            override fun getAllMemos(): List<Memo> {
                getAllMemoCallCount++
                return localMemos
            }

            override fun getMemo(id: String): Memo? {
                error("")
            }

            override fun deleteMemo(id: String) {
                /*Nothing*/
            }
        })

        // when
        memoRepository.getAllMemos()
        val twiceRetrievedMemos = memoRepository.getAllMemos()

        // then
        assertAll(
            { assertThat(twiceRetrievedMemos).containsExactlyElementsIn(localMemos) },
            { assertThat(getAllMemoCallCount).isEqualTo(1) },
        )
    }

    @Test
    fun `처음 불러오는 모든 메모는 반드시 캐시가 아닌 Source 객체에서 가져온다`() {
        // given
        var getAllMemoCallCount = 0
        val memoRepository = MemosRepository(object : MemosSource {
            override fun save(memo: Memo) {
                /*Nothing*/
            }

            override fun getAllMemos(): List<Memo> {
                getAllMemoCallCount++
                return emptyList()
            }

            override fun getMemo(id: String): Memo? {
                error("")
            }

            override fun deleteMemo(id: String) {
                /*Nothing*/
            }
        })

        // when
        memoRepository.getAllMemos()

        // then
        assertThat(getAllMemoCallCount).isEqualTo(1)
    }

    @Test
    fun `초기 상태일 때, 메모를 저장하고 모든 메모를 불러오면 캐시된 메모가 아닌 source 객체에서 불러온다`() {
        // given
        var getAllMemoCallCount = 0
        val memoRepository = MemosRepository(object : MemosSource {
            override fun save(memo: Memo) {
                /*Nothing*/
            }

            override fun getAllMemos(): List<Memo> {
                getAllMemoCallCount++
                return emptyList()
            }

            override fun getMemo(id: String): Memo? {
                error("")
            }

            override fun deleteMemo(id: String) {
                /*Nothing*/
            }
        })

        // when
        memoRepository.save(Memo("title", "content", "1"))
        memoRepository.getAllMemos()

        // then
        assertThat(getAllMemoCallCount).isEqualTo(1)
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
        var getMemoCallCount = 0
        val memoRepository = MemosRepository(object : MemosSource {
            override fun save(memo: Memo) {
                /*Nothing*/
            }

            override fun getAllMemos(): List<Memo> {
                return emptyList()
            }

            override fun getMemo(id: String): Memo? {
                getMemoCallCount++
                return memo
            }

            override fun deleteMemo(id: String) {
                /*Nothing*/
            }
        })

        // when
        memoRepository.save(memo)
        val retrieveMemo = memoRepository.getMemo("1")


        // then
        assertAll(
            { assertThat(memo).isEqualTo(retrieveMemo) },
            { assertThat(getMemoCallCount).isEqualTo(0) },
        )
    }

    @Test
    fun `특정 메모를 가져오지 못하면, 특정 메모를 찾아 반환한다`() {
        // given
        val memo = Memo("title", "content", "1")
        var getMemoCallCount = 0
        val memoRepository = MemosRepository(object : MemosSource {
            override fun save(memo: Memo) {
                /*Nothing*/
            }

            override fun getAllMemos(): List<Memo> {
                return emptyList()
            }

            override fun getMemo(id: String): Memo? {
                getMemoCallCount++
                return memo
            }

            override fun deleteMemo(id: String) {
                /*Nothing*/
            }
        })

        // when
        val retrieveMemo = memoRepository.getMemo("1")

        // then
        assertThat(memo).isEqualTo(retrieveMemo)
    }

    @Test
    fun `특정 메모를 캐시에서 가져오지 못했다면, 다음 모든 메모는 source에서 가져온다`() {
        // given
        var getMemoCallCount = 0
        var getAllMemoCallCount = 0
        val memoRepository = MemosRepository(object : MemosSource {
            override fun save(memo: Memo) {
                /*Nothing*/
            }

            override fun getAllMemos(): List<Memo> {
                getAllMemoCallCount++
                return emptyList()
            }

            override fun getMemo(id: String): Memo? {
                getMemoCallCount++
                return Memo("title", "content", "1")
            }

            override fun deleteMemo(id: String) {
                /*Nothing*/
            }
        })

        // when
        memoRepository.getMemo("1")

        // then
        assertThat(getMemoCallCount).isEqualTo(1)

        // when
        memoRepository.getAllMemos()

        // then
        assertThat(getAllMemoCallCount).isEqualTo(1)
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
