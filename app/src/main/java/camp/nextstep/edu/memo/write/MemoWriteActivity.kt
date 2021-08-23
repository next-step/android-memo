package camp.nextstep.edu.memo.write

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import camp.nextstep.edu.memo.MemoEvent
import camp.nextstep.edu.memo.R
import camp.nextstep.edu.memo.databinding.ActivityMemoWriteBinding
import camp.nextstep.edu.memo.launchAndRepeatOnLifecycle
import kotlinx.coroutines.flow.collect

class MemoWriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemoWriteBinding
    private val viewModel by viewModels<MemoWriteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupObserver()
    }

    private fun setupObserver() {
        launchAndRepeatOnLifecycle(scope = lifecycleScope, owner = this) {
            viewModel.memoEvent.collect {
                when (it) {
                    MemoEvent.Write -> {
                        setResult(RESULT_OK)
                        finish()
                    }
                    MemoEvent.Cancel -> {
                        setResult(RESULT_CANCELED)
                        finish()
                    }
                    MemoEvent.Update,
                    is MemoEvent.Delete,
                    MemoEvent.None -> Unit
                }.javaClass
            }
        }
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_memo_write)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    companion object {

        fun intent(context: Context) = Intent(context, MemoWriteActivity::class.java)
    }
}
