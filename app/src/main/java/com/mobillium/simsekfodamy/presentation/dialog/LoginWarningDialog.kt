package com.mobillium.simsekfodamy.presentation.dialog

import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.DialogLoginWarningBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginWarningDialog : BaseFragment<LoginWarningDialogViewModel, DialogLoginWarningBinding>(
    R.layout.dialog_login_warning,
    LoginWarningDialogViewModel::class.java
)
