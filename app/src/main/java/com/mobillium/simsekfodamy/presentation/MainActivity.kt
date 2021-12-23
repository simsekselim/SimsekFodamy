package com.mobillium.simsekfodamy.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.databinding.ActivityMainBinding
import com.mobillium.simsekfodamy.presentation.loginflow.adapter.MainPagerAdapter
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


}