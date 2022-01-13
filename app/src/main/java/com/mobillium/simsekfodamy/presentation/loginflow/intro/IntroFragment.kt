package com.mobillium.simsekfodamy.presentation.loginflow.intro

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentIntroBinding
import com.mobillium.simsekfodamy.presentation.loginflow.adapter.ViewPagerAdapter

class FragmentIntro : BaseFragment<IntroViewModel, FragmentIntroBinding>(
    R.layout.fragment_intro,
    IntroViewModel::class.java
) {

    @SuppressLint(RESOURCE_AS_COLOR)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cancel.setOnClickListener {
            viewModel.cancel()
        }
        fun postToList() {
            viewModel.addToList(
                getString(R.string.title_one),
                getString(R.string.description),
                R.drawable.intro1
            )
            viewModel.addToList(
                getString(R.string.title_two),
                getString(R.string.description),
                R.drawable.intro2
            )
            viewModel.addToList(
                getString(R.string.title_three),
                getString(R.string.description),
                R.drawable.intro3
            )
            viewModel.addToList(
                getString(R.string.title_four),
                getString(R.string.description),
                R.drawable.intro4
            )
        }
        postToList()
        val viewPager = binding.viewPager2
        viewPager.adapter = ViewPagerAdapter(viewModel.titlesList, viewModel.descList, viewModel.imagesList)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.indicator.setViewPager2(viewPager)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override
            fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == viewModel.imagesList.size - 1) {
                    binding.swipe.text = getString(R.string.Start)
                } else {
                    binding.swipe.text = getString(R.string.GoAhead)
                }
            }
        })

        binding.swipe.setOnClickListener {
            if (viewPager.currentItem == viewModel.imagesList.lastIndex) {
                Navigation.findNavController(it).navigate(R.id.fragmentLogin)
            } else {
                viewPager.setCurrentItem(viewPager.currentItem + 1, true)
            }
        }
    }
    companion object {
        const val RESOURCE_AS_COLOR = "ResourceAsColor"
    }
}
