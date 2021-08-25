package camp.nextstep.edu.memo.main

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import camp.nextstep.edu.memo.MemoEvent
import camp.nextstep.edu.memo.R
import camp.nextstep.edu.memo.databinding.ActivityMainBinding
import camp.nextstep.edu.memo.delete.MemoDeleteDialogFragment
import camp.nextstep.edu.memo.launchAndRepeatOnLifecycle
import camp.nextstep.edu.memo.update.MemoUpdateActivity
import camp.nextstep.edu.memo.write.MemoWriteActivity
import java.util.UUID
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val mainAdapter by lazy {
        MainAdapter(
            onUpdate = ::onUpdate,
            onDelete = ::onDelete
        )
    }

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
                mainAdapter.submitList(it)
            }
        }
        viewModel.memoEvent.observe(this) {
            val event = it.getContentIfNotHandled() ?: return@observe
            when (event) {
                is MemoEvent.Delete -> {
                    showContent()
                }
                MemoEvent.Cancel,
                MemoEvent.None,
                MemoEvent.Update,
                MemoEvent.Write -> Unit
            }.javaClass
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

    private fun onUpdate(uuid: UUID) {
        activityResultRegistry
            .register(
                KEY_MEMO_UPDATE,
                ActivityResultContracts.StartActivityForResult()
            ) {
                if (it.resultCode != RESULT_OK) return@register
                showContent()
            }
            .launch(MemoUpdateActivity.intent(context = this).apply {
                putExtras(bundleOf(MemoUpdateActivity.BUNDLE_KEY_ITEM_ID to uuid))
            })
    }

    private fun onDelete(uuid: UUID) {
        MemoDeleteDialogFragment
            .newInstance(uuid)
            .apply {
                show(supportFragmentManager, MemoDeleteDialogFragment.TAG)
            }
    }

    companion object {
        private const val KEY_MEMO_WRITE = "key_memo_write"
        private const val KEY_MEMO_UPDATE = "key_memo_update"
    }
}
