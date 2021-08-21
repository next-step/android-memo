package camp.nextstep.edu.memo.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import camp.nextstep.edu.memo.domain.entity.Memo

class MainAdapter(
    val onUpdate: (position: Int) -> Unit,
    val onDelete: (position: Int) -> Unit
) : RecyclerView.Adapter<MainViewHolder>() {

    private val items = mutableListOf<Memo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(parent = parent).apply {
            itemView.setOnClickListener {
                onUpdate(adapterPosition)
            }
            itemView.setOnLongClickListener {
                onDelete(adapterPosition)
                true
            }
        }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun replaceItems(items: List<Memo>) {
        this.items.run {
            clear()
            addAll(items)
        }
    }
}
