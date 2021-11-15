package com.mobillium.simsekfodamy.base

import android.app.Application
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<TViewModel: BaseViewModel>(
    @LayoutRes private val layoutResId: Int,
    private val viewModelType: Class<TViewModel>)
    : Fragment() {

    protected lateinit var viewModel: TViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
