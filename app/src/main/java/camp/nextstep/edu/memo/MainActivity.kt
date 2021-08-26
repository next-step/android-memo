package camp.nextstep.edu.memo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.memo.databinding.ActivityMainBinding
import camp.nextstep.edu.memo.edit.EditMemoActivity

class MainActivity : AppCompatActivity() {

    private val memosAdapter: MemosAdapter by lifeCycled { MemosAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        initView(binding)
    }

    private fun initView(binding: ActivityMainBinding) {
        setContentView(binding.root)
        binding.buttonCreate.setOnClickListener { deployAddMemoActivity() }
        binding.listMemos.adapter = memosAdapter
    }

    private fun deployAddMemoActivity() {
        Intent(this, EditMemoActivity::class.java)
            .also { startActivity(it) }
    }
}
