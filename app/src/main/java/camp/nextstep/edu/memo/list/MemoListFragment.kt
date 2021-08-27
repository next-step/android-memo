package camp.nextstep.edu.memo.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import camp.nextstep.edu.memo.R
import camp.nextstep.edu.memo.databinding.FragmentMemoListBinding

class MemoListFragment : Fragment() {

    private lateinit var binding: FragmentMemoListBinding
    private val adapter: MemoAdapter by lazy {
        MemoAdapter(
            clickEvent = { memoId -> },
            longClickEvent = { memoId -> showDeleteDialog(memoId) },
        )
    }
    private val viewModel by viewModels<MemosViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_memo_list, container, false)
        binding = FragmentMemoListBinding.bind(root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initListener()
    }

    private fun initAdapter() {
        with(binding) {
            recyclerView.adapter = adapter
        }
    }

    private fun initListener() {
        with(binding) {
            buttonCreate.setOnClickListener { findNavController().navigate(R.id.navigation_memo_add) }
        }
    }

    private fun showDeleteDialog(memoId: String) {
        AlertDialog.Builder(activity)
            .setMessage(R.string.dialog_check_delete_memo)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.ok) { _, _ ->
                viewModel.deleteMemo(memoId)
            }
            .create()
            .show()
    }

    companion object {
        fun newInstance() = MemoListFragment()
    }
}