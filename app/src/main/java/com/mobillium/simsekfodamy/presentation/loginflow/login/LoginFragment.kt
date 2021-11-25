package com.mobillium.simsekfodamy.presentation.loginflow.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>(
    R.layout.fragment_login,
    LoginViewModel::class.java
) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        viewModel.navigateMain.observe(viewLifecycleOwner, {
            val navigateMain = LoginFragmentDirections.actionFragmentLoginToMainFragment()
            findNavController().navigate(navigateMain)
        })

        binding.register.setOnClickListener {
            findNavController().navigate(R.id.fragmentRegister)
        }


        binding.forget.setOnClickListener {
            findNavController().navigate(R.id.fragmentForgetPassword)
        }



        binding.login.setOnClickListener {


            viewModel.onClickLogin()


        }


    }




}


