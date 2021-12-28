package com.mobillium.simsekfodamy.presentation.homeflow.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mobillium.simsekfodamy.presentation.homeflow.editor.EditorFragment
import com.mobillium.simsekfodamy.presentation.homeflow.last.LastFragment

class HomePagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                EditorFragment()
            }
            1 -> {
                LastFragment()
            }

            else -> Fragment()
        }
    }
}
