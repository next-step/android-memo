package camp.nextstep.edu.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import camp.nextstep.edu.memo.databinding.ActivityEditMemoBinding

class EditMemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityEditMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
