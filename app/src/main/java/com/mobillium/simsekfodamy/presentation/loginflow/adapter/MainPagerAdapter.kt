package com.mobillium.simsekfodamy.presentation.loginflow.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mobillium.simsekfodamy.presentation.favorites.FavoritesFragment
import com.mobillium.simsekfodamy.presentation.homeflow.home.HomeFragment
import com.mobillium.simsekfodamy.presentation.profile.ProfileFragment

class MainPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = 3


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomeFragment()
            }
            1 -> {
                FavoritesFragment()
            }
            2 -> {
                ProfileFragment()
            }
            else -> Fragment()
        }
    }



}
