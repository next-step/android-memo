package camp.nextstep.edu.data

import camp.nextstep.edu.data.local.MemosLocalSource
import camp.nextstep.edu.data.repository.RealMemosRepository
import camp.nextstep.edu.domain.MemosRepository

/**
 * Created By Malibin
 * on 9월 07, 2021
 */

object MemosRepositoryInjector {

    private var instance: MemosRepository? = null

    @JvmStatic
    fun provideMemosRepository(): MemosRepository = synchronized(this) {
        instance ?: RealMemosRepository(MemosLocalSource())
            .also { this.instance = it }
    }
}
