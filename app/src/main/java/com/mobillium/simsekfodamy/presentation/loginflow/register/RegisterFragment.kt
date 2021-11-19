package com.mobillium.simsekfodamy.presentation.loginflow.register

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterViewModel, FragmentRegisterBinding>(
    R.layout.fragment_register,
    RegisterViewModel::class.java
) {





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        viewModel.navigateLogin.observe(viewLifecycleOwner, {
            val navigateLogin = RegisterFragmentDirections.actionFragmentRegisterToFragmentLogin()
            findNavController().navigate(navigateLogin)
        })

        binding.register.setOnClickListener {
            viewModel.onClickRegister()
        }

        binding.login.setOnClickListener {
            findNavController().navigate(R.id.fragmentLogin)
        }


    }
}