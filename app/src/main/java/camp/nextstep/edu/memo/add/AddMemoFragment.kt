package camp.nextstep.edu.memo.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import camp.nextstep.edu.memo.R
import camp.nextstep.edu.memo.databinding.FragmentMemoAddBinding
import camp.nextstep.edu.memo.util.EventObserver
import camp.nextstep.edu.memo.util.exhaustive

class AddMemoFragment : Fragment() {
    private lateinit var binding: FragmentMemoAddBinding
    private val viewModel by viewModels<AddMemoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_memo_add, container, false)
        binding = FragmentMemoAddBinding.bind(root)
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
    }

    private fun handleEvent(event: MemoEvent) {
        when (event) {
            MemoEvent.Cancel -> findNavController().navigateUp()
            MemoEvent.Complete -> findNavController().navigate(R.id.navigation_memo_list)
        }.exhaustive
    }

    companion object {
        fun newInstance() = AddMemoFragment()
    }
}