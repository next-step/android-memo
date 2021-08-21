package camp.nextstep.edu.memo.delete

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import camp.nextstep.edu.memo.MemoEvent
import camp.nextstep.edu.memo.R
import camp.nextstep.edu.memo.databinding.ActivityMemoDeleteBinding
import camp.nextstep.edu.memo.launchAndRepeatOnLifecycle
import kotlinx.coroutines.flow.collect

class MemoDeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemoDeleteBinding
    private val viewModel by viewModels<MemoDeleteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupObserver()
    }

    private fun setupObserver() {
        launchAndRepeatOnLifecycle(scope = lifecycleScope, owner = this) {
            viewModel.memoEvent.collect {
                when (it) {
                    MemoEvent.Delete -> {
                        setResult(RESULT_OK)
                        finish()
                    }
                    MemoEvent.Cancel -> {
                        setResult(RESULT_CANCELED)
                        finish()
                    }
                    MemoEvent.None,
                    MemoEvent.Update,
                    MemoEvent.Write -> Unit
                }
            }
        }
    }

    private fun setupBinding() {
        binding = DataBindingUtil
            .setContentView<ActivityMemoDeleteBinding>(this, R.layout.activity_memo_delete)
            .also {
                it.lifecycleOwner = this
                it.position = intent.getIntExtra(BUNDLE_KEY_ITEM_POSITION, 0)
                it.viewModel = viewModel
            }
    }

    companion object {
        const val BUNDLE_KEY_ITEM_POSITION = "bundle_key_item_position"

        fun intent(context: Context) = Intent(context, MemoDeleteActivity::class.java)
    }
}
