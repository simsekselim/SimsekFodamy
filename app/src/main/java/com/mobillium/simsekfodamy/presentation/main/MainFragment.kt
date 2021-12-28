package com.mobillium.simsekfodamy.presentation.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentMainBinding
import com.mobillium.simsekfodamy.presentation.homeflow.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>(
    R.layout.fragment_main,
    MainViewModel::class.java
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val adapter = MainPagerAdapter(childFragmentManager, lifecycle)
//        binding.viewPager.adapter = adapter
//        val bottomNavigationView = binding.bottomNavigationView
//        val viewPager = binding.viewPager
//
//
//
//        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                bottomNavigationView.menu.getItem(position).setChecked(true)
//            }
//        })
//
//
//        bottomNavigationView.setOnNavigationItemSelectedListener() { menuItem ->
//            viewPager.setCurrentItem(
//                when (menuItem.itemId) {
//                    R.id.home -> 0
//                    R.id.favorites -> 1
//                    R.id.profile -> 2
//                    else -> throw Exception()
//
//                },
//                false
//            )
//            return@setOnNavigationItemSelectedListener true
//        }

        binding.toolbar.ivLogout.setOnClickListener {
            viewModel.logout()
        }
        lifecycleScope.launch {
            viewModel.getToken().asLiveData().observe(viewLifecycleOwner, {
                // Change the Icon
                // Show the message
            })
        }
        viewModel.navigateLogin.observe(viewLifecycleOwner, {
            val navigateLogin = HomeFragmentDirections.actionHomeFragmentToFragmentLogin()
            findNavController().navigate(navigateLogin)
        })
    }
}
