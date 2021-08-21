package camp.nextstep.edu.memo.delete

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import camp.nextstep.edu.memo.R
import camp.nextstep.edu.memo.databinding.ActivityMemoDeleteBinding

class MemoDeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemoDeleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_memo_delete)
        binding.lifecycleOwner = this
    }
}
