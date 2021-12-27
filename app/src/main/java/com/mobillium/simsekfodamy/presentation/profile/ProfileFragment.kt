package com.mobillium.simsekfodamy.presentation.profile

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentProfileBinding
import com.mobillium.simsekfodamy.model.User
import com.mobillium.simsekfodamy.presentation.homeflow.home.HomeFragmentDirections

import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlin.math.log


@AndroidEntryPoint
class ProfileFragment :
    BaseFragment<ProfileViewModel, FragmentProfileBinding>(
        R.layout.fragment_profile,
        ProfileViewModel::class.java
    ) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.toolbar.ivBack.isVisible = false
        binding.toolbar.tvBack.isVisible = false
        binding.login.isVisible = false

        viewModel.user.observe(viewLifecycleOwner,{ user ->
            binding.profile = user

        })

        binding.toolbar.ivLogout.setOnClickListener {
            viewModel.logout()
            Toast.makeText(context,"Çıkış Yapıldı", Toast.LENGTH_LONG).show()
        }

        viewModel.navigateLogin.observe(viewLifecycleOwner, {
            val navigateLogin = ProfileFragmentDirections.actionProfileFragmentToFragmentLogin()
            findNavController().navigate(navigateLogin)
        })


    }
}



