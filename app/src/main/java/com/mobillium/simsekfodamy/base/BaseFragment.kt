package com.mobillium.simsekfodamy.base

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.utils.findGenericSuperclass

abstract class BaseFragment<TViewModel : BaseViewModel, TBinding : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int,
    private val viewModelType: Class<TViewModel>
) : Fragment() {

    @Suppress("UNCHECKED_CAST")
    val viewModelClass: Class<TViewModel>
        get() = findGenericSuperclass<BaseFragment<TViewModel, TBinding>>()
            ?.actualTypeArguments
            ?.getOrNull(1) as? Class<TViewModel>
            ?: throw IllegalStateException("viewModelClass does not equal Class<VM>")

    protected lateinit var viewModel: TViewModel
    protected lateinit var binding: TBinding

    // loading dialog
    private var dialog: Dialog? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog = Dialog(requireActivity())
        viewModel = ViewModelProvider(this).get(viewModelType)
        dialog!!.setCancelable(false)
        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog!!.setContentView(R.layout.loading)
        dialog!!.window!!.statusBarColor = ContextCompat.getColor(requireContext(), R.color.red)
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.baseEvent.observe(viewLifecycleOwner,{
                onViewEvent(it)
            })

        }
    }
    private fun onViewEvent(event: BaseViewEvent) {
        when (event) {
            is BaseViewEvent.NavigateTo ->
                findNavController().navigate(event.directions)

            is BaseViewEvent.NavigateBack ->
                findNavController().popBackStack()
            is BaseViewEvent.ShowLoading -> {
                if (event.isShow) dialog?.show() else dialog?.dismiss()
            }
        }
    }
}
