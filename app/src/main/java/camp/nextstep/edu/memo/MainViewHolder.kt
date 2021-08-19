package camp.nextstep.edu.memo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import camp.nextstep.edu.memo.databinding.ItemMemoBinding
import camp.nextstep.edu.memo.domain.entity.Memo

class MainViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context)
        .inflate(R.layout.item_memo, parent, false)
) {

    private val binding: ItemMemoBinding = ItemMemoBinding.bind(itemView)

    fun bind(item: Memo) = with(binding) {
        textView.text = item.value
    }
}
