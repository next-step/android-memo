package camp.nextstep.edu.memo

sealed class MemoEvent {

    object None : MemoEvent()

    object Write : MemoEvent()

    object Update : MemoEvent()

    object Delete : MemoEvent()

    object Cancel : MemoEvent()

}
