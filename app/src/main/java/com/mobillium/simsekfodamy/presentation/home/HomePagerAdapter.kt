package com.mobillium.simsekfodamy.presentation.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mobillium.simsekfodamy.presentation.favorites.FavoritesFragment
import com.mobillium.simsekfodamy.presentation.loginflow.login.LoginFragment
import com.mobillium.simsekfodamy.presentation.loginflow.register.RegisterFragment
import com.mobillium.simsekfodamy.presentation.profile.ProfileFragment

class HomePagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = 2


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                Fragment()
            }
            1 -> {
                RegisterFragment()
            }

            else -> Fragment()
        }
    }



}
