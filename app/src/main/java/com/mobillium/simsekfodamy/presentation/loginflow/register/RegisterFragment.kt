package com.mobillium.simsekfodamy.presentation.loginflow.register

import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterViewModel, FragmentRegisterBinding>(
    R.layout.fragment_register,
    RegisterViewModel::class.java
)
