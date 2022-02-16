package com.mobillium.simsekfodamy.presentation.homeflow.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.google.android.material.tabs.TabLayoutMediator
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentHomeBinding
import com.mobillium.simsekfodamy.presentation.homeflow.adapter.HomePagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<HomeViewModel, FragmentHomeBinding>(
        R.layout.fragment_home,
        HomeViewModel::class.java
    ) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = HomePagerAdapter(childFragmentManager, lifecycle)
        binding.pager.adapter = pagerAdapter
        binding.toolbar.ivBack.isVisible = false
        binding.toolbar.tvBack.isVisible = false
        TabLayoutMediator(binding.layout, binding.pager) { tab, position ->

            when (position) {
                0 -> {
                    tab.text = getString(R.string.editor_choose)
                }
                1 -> {
                    tab.text = getText(R.string.last_add)
                }
                else -> {
                    tab.text = getString(R.string.editor_choose)
                }
            }
        }.attach()

        binding.toolbar.ivLogout.setOnClickListener {
            viewModel.logout()
        }
    }
}
