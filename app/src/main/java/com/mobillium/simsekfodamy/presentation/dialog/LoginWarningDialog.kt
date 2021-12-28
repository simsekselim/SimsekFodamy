package com.mobillium.simsekfodamy.presentation.dialog

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.DialogLoginWarningBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginWarningDialog : BaseFragment<LoginWarningDialogViewModel, DialogLoginWarningBinding>(
    R.layout.dialog_login_warning,
    LoginWarningDialogViewModel::class.java
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.login.setOnClickListener {
            findNavController().navigate(R.id.fragmentLogin)
        }
        binding.cancel.setOnClickListener {
            findNavController().navigate(R.id.home)
        }
    }
}
