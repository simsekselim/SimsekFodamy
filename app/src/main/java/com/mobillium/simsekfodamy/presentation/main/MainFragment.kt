package com.mobillium.simsekfodamy.presentation.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentMainBinding
import com.mobillium.simsekfodamy.presentation.loginflow.adapter.MainPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.text.FieldPosition

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>(
    R.layout.fragment_main,
    MainViewModel::class.java
) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MainPagerAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        val bottomNavigationView = binding.bottomNavigationView
        val viewPager = binding.viewPager


        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomNavigationView.menu.getItem(position).setChecked(true)
            }
        })


        bottomNavigationView.setOnNavigationItemSelectedListener() { menuItem ->
            viewPager.setCurrentItem(
                when (menuItem.itemId) {
                    R.id.home -> 0
                    R.id.favorites -> 1
                    R.id.profile -> 2
                    else -> throw Exception()

                },
                false
            )
            return@setOnNavigationItemSelectedListener true
        }


    }

}


