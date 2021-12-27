package com.mobillium.simsekfodamy.presentation.loginflow.login

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
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
        binding.error.isVisible = false
        binding.errorMessage.isVisible = false
        viewModel.navigateMain.observe(viewLifecycleOwner, {
            val navigateMain = LoginFragmentDirections.actionFragmentLoginToHome()
            findNavController().navigate(navigateMain)
        })

        binding.register.setOnClickListener {
            findNavController().navigate(R.id.fragmentRegister)
        }


        binding.forget.setOnClickListener {
            findNavController().navigate(R.id.fragmentForgetPassword)
        }





        binding.login.setOnClickListener {

            if (viewModel.username.value.isNullOrBlank()){
                binding.error.isVisible = true
                binding.errorMessage.isVisible = true
                binding.errorMessage.text = "Kullanıcı adı alanı boş bırakılamaz."
            }else if (viewModel.password.value.isNullOrBlank()){
                binding.error.isVisible = true
                binding.errorMessage.isVisible = true
                binding.errorMessage.text = "Şifre alanı boş bırakılamaz."

            }


            viewModel.onClickLogin()


        }


    }




}


