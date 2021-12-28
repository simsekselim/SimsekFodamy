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

        binding.apply {
            register.setOnClickListener {
                viewModel?.onClickRegister()

                if (userName.text.toString().isNullOrBlank()) {
                    userName.setError("Please enter username")
                    userName.requestFocus()
                    userName.isEnabled = true
                }
                if (userMail.text.toString().isNullOrBlank()) {
                    userMail.setError("Please enter mail")
                    userMail.requestFocus()
                    userMail.isEnabled = true
                }
                if (userPassword.text.toString().length < 6) {
                    userPassword.setError("Password minimum contain 6 character")
                    userPassword.requestFocus()
                    userPassword.isEnabled = true
                }
                if (userPassword.text.toString().equals("")) {
                    userPassword.setError("Please enter password")
                    userPassword.requestFocus()
                }
            }
        }

        binding.login.setOnClickListener {
            findNavController().navigate(R.id.fragmentLogin)
        }
    }
}
