package com.mobillium.simsekfodamy.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment :
    BaseFragment<ProfileViewModel, FragmentProfileBinding>(
        R.layout.fragment_profile,
        ProfileViewModel::class.java
    ) {

    override val isSharedViewModel: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.ivBack.isVisible = false
        binding.toolbar.tvBack.isVisible = false
        binding.login.isVisible = false

        viewModel.user.observe(viewLifecycleOwner) { user ->
            binding.profile = user
        }

        binding.toolbar.ivLogout.setOnClickListener {
            viewModel.logout()
        }
    }
}
