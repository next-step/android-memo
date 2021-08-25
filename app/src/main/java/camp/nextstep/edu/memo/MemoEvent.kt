package camp.nextstep.edu.memo

import java.util.UUID

sealed class MemoEvent {

    object None : MemoEvent()

    object Write : MemoEvent()

    object Update : MemoEvent()

    data class Delete(val uuid: UUID) : MemoEvent()

    object Cancel : MemoEvent()

}
