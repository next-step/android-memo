package camp.nextstep.edu.memo.update

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import camp.nextstep.edu.memo.R
import camp.nextstep.edu.memo.databinding.ActivityMemoUpdateBinding

class MemoUpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemoUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_memo_update)
        binding.lifecycleOwner = this
    }

    companion object {
        const val BUNDLE_KEY_ITEM_POSITION = "bundle_key_item_position"

        fun intent(context: Context) = Intent(context, MemoUpdateActivity::class.java)
    }
}
