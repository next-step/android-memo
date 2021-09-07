package camp.nextstep.edu.data

import camp.nextstep.edu.data.local.MemosLocalSource
import camp.nextstep.edu.data.repository.MemosRepository
import camp.nextstep.edu.domain.MemosSource

/**
 * Created By Malibin
 * on 9월 07, 2021
 */

object MemosRepositoryInjector {

    private var instance: MemosRepository? = null

    @JvmStatic
    fun provideMemosRepository(): MemosSource = synchronized(this) {
        instance ?: MemosRepository(MemosLocalSource())
            .also { this.instance = it }
    }
}
