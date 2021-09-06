package camp.nextstep.edu.memo.edit

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.memo.databinding.ActivityEditMemoBinding
import camp.nextstep.edu.memo.utils.ViewModelFactory

class EditMemoActivity : AppCompatActivity() {
    private val editMemoViewModel: EditMemoViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityEditMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = editMemoViewModel
        editMemoViewModel.loadMemo(getMemoId())

        editMemoViewModel.memoSaved.observe(this) {
            setResult(Activity.RESULT_OK)
            finish()
        }
        editMemoViewModel.toastMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getMemoId(): String? = intent.getStringExtra(KEY_MEMO_ID)

    companion object {
        const val KEY_MEMO_ID = "KEY_MEMO_ID"
    }
}
