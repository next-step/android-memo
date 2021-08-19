package camp.nextstep.edu.memo.write

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import camp.nextstep.edu.memo.MemoEvent
import camp.nextstep.edu.memo.R
import camp.nextstep.edu.memo.databinding.ActivityMemoWriteBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MemoWriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemoWriteBinding
    private val viewModel by viewModels<MemoWriteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupObserver()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isSavedMemo.collect {
                    when (it) {
                        MemoEvent.Write -> {
                            setResult(RESULT_OK)
                            finish()
                        }
                        MemoEvent.Cancel -> {
                            setResult(RESULT_CANCELED)
                            finish()
                        }
                        else -> Unit
                    }
                }
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
