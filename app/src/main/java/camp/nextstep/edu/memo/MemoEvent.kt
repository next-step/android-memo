package camp.nextstep.edu.memo

sealed class MemoEvent {

    object None : MemoEvent()

    object Write : MemoEvent()

    object Update : MemoEvent()

    data class Delete(val position: Int) : MemoEvent()

    object Cancel : MemoEvent()

}
