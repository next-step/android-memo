package camp.nextstep.edu.memo.delete

import android.content.Context
import android.content.Intent
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

    companion object {
        const val BUNDLE_KEY_ITEM_POSITION = "bundle_key_item_position"

        fun intent(context: Context) = Intent(context, MemoDeleteActivity::class.java)
    }
}
