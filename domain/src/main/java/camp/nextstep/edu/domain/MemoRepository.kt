package camp.nextstep.edu.domain

interface MemoRepository {
    fun findMemos(): List<String>
}
