package com.mobillium.simsekfodamy.presentation.loginflow.forgetpassword

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentForgetpasswordBinding

class FragmentForgetPassword : BaseFragment<ForgetPasswordViewModel, FragmentForgetpasswordBinding>(
    R.layout.fragment_forgetpassword,
    ForgetPasswordViewModel::class.java
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.fragmentLogin)
        }
    }
}
