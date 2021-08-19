package camp.nextstep.edu.memo

sealed class MemoEvent {

    object None : MemoEvent()

    object Write : MemoEvent()

    object Cancel : MemoEvent()

}
