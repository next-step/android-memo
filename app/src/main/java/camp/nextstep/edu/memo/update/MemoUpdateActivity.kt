package camp.nextstep.edu.memo.update

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import camp.nextstep.edu.memo.MemoEvent
import camp.nextstep.edu.memo.R
import camp.nextstep.edu.memo.databinding.ActivityMemoUpdateBinding
import camp.nextstep.edu.memo.launchAndRepeatOnLifecycle
import kotlinx.coroutines.flow.collect

class MemoUpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemoUpdateBinding
    private val viewModel by viewModels<MemoUpdateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupObserver()
    }

    private fun setupObserver() {
        launchAndRepeatOnLifecycle(scope = lifecycleScope, owner = this) {
            viewModel.memoEvent.collect {
                when (it) {
                    MemoEvent.Update -> {
                        setResult(RESULT_OK)
                        finish()
                    }
                    MemoEvent.Cancel -> {
                        setResult(RESULT_CANCELED)
                        finish()
                    }
                    MemoEvent.None,
                    MemoEvent.Delete,
                    MemoEvent.Write -> Unit
                }
            }
        }
    }

    private fun setupBinding() {
        binding = DataBindingUtil
            .setContentView<ActivityMemoUpdateBinding>(this, R.layout.activity_memo_update)
            .also {
                it.lifecycleOwner = this
                it.position = intent.getIntExtra(BUNDLE_KEY_ITEM_POSITION, 0)
                it.viewModel = viewModel
            }
    }

    companion object {
        const val BUNDLE_KEY_ITEM_POSITION = "bundle_key_item_position"

        fun intent(context: Context) = Intent(context, MemoUpdateActivity::class.java)
    }
}
