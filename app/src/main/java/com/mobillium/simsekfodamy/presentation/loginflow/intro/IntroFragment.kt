package com.mobillium.simsekfodamy.presentation.loginflow.intro

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.presentation.loginflow.adapter.ViewPagerAdapter
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator

private var titlesList = mutableListOf<String>()
private var descList = mutableListOf<String>()
private var imagesList = mutableListOf<Int>()

class FragmentIntro : Fragment() {
    var swipe: Button? = null

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fun addToList(
            title: String,
            description: String,
            image: Int
        ) {
            titlesList.add(title)
            descList.add(description)
            imagesList.add(image)
        }

        val cancel: Button = view.findViewById(R.id.cancel)
        cancel.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.fragmentLogin)
        }
        fun postToList() {
            addToList(
                "Welcome to Fodamy Network! ",
                "Fodamy is the best place to find your favourite recipes in all around the world. ",
                R.drawable.intro1
            )
            addToList(
                "Finding recipes were not that easy. ",
                "Fodamy is the best place to find your favourite recipes in all around the world.  ",
                R.drawable.intro2
            )
            addToList(
                "Add new recipe. ",
                "Fodamy is the best place to find your favourite recipes in all around the world.  ",
                R.drawable.intro3
            )
            addToList(
                "Share recipe with others. ",
                "Fodamy is the best place to find your favourite recipes in all around the world.  ",
                R.drawable.intro4
            )
        }
        postToList()
        val view_pager2 = view.findViewById<ViewPager2>(R.id.view_pager2)
        view_pager2.adapter = ViewPagerAdapter(titlesList, descList, imagesList)
        view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        // Indicator
        val indicator: SpringDotsIndicator = view.findViewById<SpringDotsIndicator>(R.id.indicator)
        indicator.setViewPager2(view_pager2)
        view_pager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override
            fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == imagesList.size - 1) {
                    swipe = view.findViewById(R.id.swipe)
                    swipe?.text = getString(R.string.Start)
                } else {
                    swipe?.text = getString(R.string.GoAhead)
                }
            }
        })
        swipe = view.findViewById(R.id.swipe)
        swipe?.setOnClickListener {
            if (view_pager2.currentItem == imagesList.lastIndex) {
                Navigation.findNavController(it).navigate(R.id.fragmentLogin)
            } else {
                view_pager2.setCurrentItem(view_pager2.currentItem + 1, true)
            }
        }
    }
}
