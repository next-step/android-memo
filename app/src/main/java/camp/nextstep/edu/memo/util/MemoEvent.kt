package camp.nextstep.edu.memo.util

sealed class MemoEvent {
    object Complete : MemoEvent()
    object Cancel : MemoEvent()
}