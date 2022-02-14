package com.mobillium.simsekfodamy.presentation.bottom.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mobillium.simsekfodamy.BR
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseViewEvent
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.utils.Constants.DIALOG_ACTION
import com.mobillium.simsekfodamy.utils.FetchExtras
import com.mobillium.simsekfodamy.utils.snackbar

abstract class BottomBaseFragment<TViewModel : BaseViewModel, TBinding : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int,
    private val viewModelType: Class<TViewModel>
) : BottomSheetDialogFragment(), FetchExtras {

    protected lateinit var viewModel: TViewModel
    protected lateinit var binding: TBinding

    private lateinit var loadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(viewModelType)
        arguments?.let {
            fetchExtras(it)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), R.style.TransparentBottomSheetDialog)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        eventObserver()
        loadingDialog = Dialog(requireActivity())
        loadingDialog.setCancelable(false)
        loadingDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        loadingDialog.setContentView(R.layout.loading)
        loadingDialog.window!!.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.red)
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    fun eventObserver() {
        viewModel.baseEvent.observe(viewLifecycleOwner) {
            onViewEvent(it)
        }
    }

    private fun onViewEvent(event: BaseViewEvent) {
        when (event) {
            is BaseViewEvent.NavigateTo ->
                findNavController().navigate(event.directions)
            is BaseViewEvent.ShowMessage ->
                snackbar(event.message.toString())
            is BaseViewEvent.Extras ->
                setFragmentResult(
                    DIALOG_ACTION,
                    bundleOf(event.key to event.value)
                )

            BaseViewEvent.NavigateBack ->
                findNavController().popBackStack()
            is BaseViewEvent.ShowLoading -> {
                if (event.isShow) dialog?.show() else dialog?.dismiss()
            }
        }
    }

    @CallSuper
    override fun fetchExtras(extras: Bundle) {
        viewModel.fetchExtras(extras)
    }
}
