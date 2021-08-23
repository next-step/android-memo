package camp.nextstep.edu.memo.delete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import camp.nextstep.edu.memo.R
import camp.nextstep.edu.memo.databinding.DialogMemoDeleteBinding
import camp.nextstep.edu.memo.main.MainViewModel
import java.util.UUID

class MemoDeleteDialogFragment : DialogFragment() {

    private lateinit var binding: DialogMemoDeleteBinding
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate<DialogMemoDeleteBinding>(inflater, R.layout.dialog_memo_delete, container, false)
            .also {
                it.lifecycleOwner = viewLifecycleOwner
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListener()
    }

    private fun setupListener() = with(binding) {
        btnMemoDeleteCancel.setOnClickListener {
            dismiss()
        }
        btnMemoDeleteConfirm.setOnClickListener {
            dismiss()
            (arguments?.get(BUNDLE_KEY_ITEM_ID) as? UUID)
                ?.let(this@MemoDeleteDialogFragment.viewModel::delete)
        }
    }

    companion object {
        private const val BUNDLE_KEY_ITEM_ID = "bundle_key_item_id"
        val TAG = MemoDeleteDialogFragment::class.java.simpleName.toString()

        fun newInstance(uuid: UUID) = MemoDeleteDialogFragment().apply {
            arguments = bundleOf(BUNDLE_KEY_ITEM_ID to uuid)
        }

    }
}
