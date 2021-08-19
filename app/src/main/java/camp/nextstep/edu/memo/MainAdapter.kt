package camp.nextstep.edu.memo

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import camp.nextstep.edu.memo.domain.entity.Memo

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private val items = mutableListOf<Memo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(parent = parent)

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
