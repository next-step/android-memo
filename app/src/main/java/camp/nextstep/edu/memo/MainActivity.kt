package camp.nextstep.edu.memo

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.memo.databinding.ActivityMainBinding
import camp.nextstep.edu.memo.edit.EditMemoActivity
import camp.nextstep.edu.memo.utils.ViewModelFactory
import camp.nextstep.edu.memo.utils.lifeCycled

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels { ViewModelFactory(this) }
    private val memosAdapter: MemosAdapter by lifeCycled { MemosAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        initView(binding)

        mainViewModel.loadAllMemos()
        mainViewModel.memos.observe(this) {
            memosAdapter.submitList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.loadAllMemos()
    }

    private fun initView(binding: ActivityMainBinding) {
        setContentView(binding.root)
        binding.buttonCreate.setOnClickListener { deployAddMemoActivity() }
        binding.listMemos.adapter = memosAdapter
        memosAdapter.setOnMemoClickListener { deployAddMemoActivity(it.id) }
    }

    private fun deployAddMemoActivity(memoId: String? = null) {
        val intent = Intent(this, EditMemoActivity::class.java)
        memoId?.run { intent.putExtra(EditMemoActivity.KEY_MEMO_ID, this) }
        startActivity(intent)
    }
}
