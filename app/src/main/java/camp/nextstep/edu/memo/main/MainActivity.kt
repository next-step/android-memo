package camp.nextstep.edu.memo.main

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import camp.nextstep.edu.memo.write.MemoWriteActivity
import camp.nextstep.edu.memo.R
import camp.nextstep.edu.memo.databinding.ActivityMainBinding
import camp.nextstep.edu.memo.launchAndRepeatOnLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val mainAdapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupRecyclerView()
        setupListener()
        setupObserver()
        showContent()
    }

    private fun setupListener() {
        binding.buttonCreate.setOnClickListener {
            activityResultRegistry
                .register(
                    KEY_MEMO_WRITE,
                    ActivityResultContracts.StartActivityForResult()
                ) {
                    if (it.resultCode != RESULT_OK) return@register
                    showContent()
                }
                .launch(MemoWriteActivity.intent(this))
        }
    }

    private fun setupObserver() {
        launchAndRepeatOnLifecycle(scope = lifecycleScope, owner = this) {
            viewModel.memoList.collect {
                mainAdapter.replaceItems(it)
            }
        }
    }

    private fun showContent() {
        viewModel.fetchMemoList()
    }

    private fun setupRecyclerView() = with(binding.recyclerView) {
        adapter = mainAdapter
        setHasFixedSize(true)
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }

    companion object {
        private const val KEY_MEMO_WRITE = "key_memo_write"
    }
}
