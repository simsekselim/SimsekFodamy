package com.mobillium.simsekfodamy.presentation.home

import android.graphics.Color
import android.media.Image
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(
    R.layout.fragment_home,
    HomeViewModel::class.java
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editor = binding.editorChoose
        val add = binding.lastAdd
        binding.editorLine.isVisible = false
        binding.addLine.isVisible = false

        editor.setOnClickListener(){
            editor.setTextColor(Color.RED)
            binding.editorLine.isVisible = true
            add.setTextColor(Color.BLACK)
            binding.addLine.isVisible = false
        }

        add.setOnClickListener(){
            add.setTextColor(Color.RED)
            binding.addLine.isVisible = true
            editor.setTextColor(Color.BLACK)
            binding.editorLine.isVisible = false
        }
    }
}