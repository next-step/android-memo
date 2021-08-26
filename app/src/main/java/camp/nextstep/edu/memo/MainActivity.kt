package camp.nextstep.edu.memo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.memo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        initView(binding)
    }

    private fun initView(binding: ActivityMainBinding) {
        setContentView(binding.root)
        binding.buttonCreate.setOnClickListener { deployAddMemoActivity() }
    }

    private fun deployAddMemoActivity() {
        Intent(this, EditMemoActivity::class.java)
            .also { startActivity(it) }
    }
}
