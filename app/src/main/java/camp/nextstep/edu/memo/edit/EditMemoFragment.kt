package camp.nextstep.edu.memo.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import camp.nextstep.edu.memo.R
import camp.nextstep.edu.memo.databinding.FragmentMemoEditBinding
import camp.nextstep.edu.memo.list.MemoListFragment
import camp.nextstep.edu.memo.util.EventObserver
import camp.nextstep.edu.memo.util.MemoEvent
import camp.nextstep.edu.memo.util.exhaustive

class EditMemoFragment : Fragment() {
    private lateinit var binding: FragmentMemoEditBinding
    private val viewModel by viewModels<EditMemoViewModel> {
        EditMemoViewModel.Factory(
            arguments?.getString(MemoListFragment.MEMO_ID) ?: throw Exception("invalid memoId")
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_memo_edit, container, false)
        binding = FragmentMemoEditBinding.bind(root)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        viewModel.actionEvent.observe(viewLifecycleOwner, EventObserver(::handleEvent))
        viewModel.errorEvent.observe(viewLifecycleOwner, EventObserver(::handleError))
    }

    private fun handleEvent(event: MemoEvent) {
        when (event) {
            MemoEvent.Cancel -> findNavController().popBackStack()
            MemoEvent.Complete -> findNavController().navigate(R.id.navigation_memo_list)
        }.exhaustive
    }


    private fun handleError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = EditMemoFragment()
    }
}