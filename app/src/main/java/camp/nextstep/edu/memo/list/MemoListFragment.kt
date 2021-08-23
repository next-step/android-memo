package camp.nextstep.edu.memo.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import camp.nextstep.edu.memo.MemoAdapter
import camp.nextstep.edu.memo.R
import camp.nextstep.edu.memo.databinding.FragmentMemoListBinding

class MemoListFragment : Fragment() {

    private lateinit var binding: FragmentMemoListBinding
    private val adapter: MemoAdapter by lazy { MemoAdapter() }
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


    companion object {
        fun newInstance() = MemoListFragment()
    }
}