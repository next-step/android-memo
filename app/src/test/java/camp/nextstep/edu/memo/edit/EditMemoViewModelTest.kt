package camp.nextstep.edu.memo.edit

import camp.nextstep.edu.memo.utils.InstantTaskExecutorExtension
import io.mockk.mockk
import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

/**
 * Created By Malibin
 * on 8월 26, 2021
 */

class EditMemoViewModelTest {

    companion object {
        @JvmField
        @RegisterExtension
        val instantTaskExecutorExtension = InstantTaskExecutorExtension()
    }

    lateinit var editMemoViewModel: EditMemoViewModel

    @Before
    fun setUp() {
        editMemoViewModel = EditMemoViewModel(mockk())
    }

    @Test
    fun ``() {
        // given


        // when


        // then


    }
}
