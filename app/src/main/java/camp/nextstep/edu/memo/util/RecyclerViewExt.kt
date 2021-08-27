package camp.nextstep.edu.memo.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import camp.nextstep.edu.memo.list.MemoAdapter
import camp.nextstep.edu.memo.domain.entity.Memo

@Suppress("UNCHECKED_CAST")
@BindingAdapter("setItems")
fun RecyclerView.setItems(list: List<Any>?) {
    list?.apply {
        (adapter as? MemoAdapter)?.setList(this as List<Memo>)
    }
}