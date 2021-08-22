package camp.nextstep.edu.memo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import camp.nextstep.edu.memo.databinding.ItemMemoBinding
import camp.nextstep.edu.memo.domain.entity.Memo

class MemoAdapter : RecyclerView.Adapter<MemoViewHolder>() {
    private val items = mutableListOf<Memo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder =
        MemoViewHolder(parent)

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setList(list: List<Memo>) {
        items.clear()
        items.addAll(list)
    }

    override fun getItemCount() = items.size

}

class MemoViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_memo, parent, false)
) {
    private val binding = ItemMemoBinding.bind(itemView)

    fun bind(memo: Memo) {
        binding.textView.text = memo.value
    }
}
