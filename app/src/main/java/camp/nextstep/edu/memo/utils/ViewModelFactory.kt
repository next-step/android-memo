package camp.nextstep.edu.memo.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.data.repository.MemosRepository
import camp.nextstep.edu.memo.MainViewModel
import camp.nextstep.edu.memo.edit.EditMemoViewModel

/**
 * Created By Malibin
 * on 8월 26, 2021
 */

class ViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MainViewModel::class.java -> createMainViewModel()
            EditMemoViewModel::class.java -> createEditMemoViewModel()
            else -> throw IllegalArgumentException("cannot find viewModel of $modelClass")
        } as T
    }

    private fun createMainViewModel(): MainViewModel {
        return MainViewModel(MemosRepository.getInstance())
    }

    private fun createEditMemoViewModel(): EditMemoViewModel {
        return EditMemoViewModel(MemosRepository.getInstance())
    }
}
