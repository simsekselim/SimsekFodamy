package com.mobillium.simsekfodamy.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.databinding.ActivityMainBinding
import com.mobillium.simsekfodamy.presentation.loginflow.adapter.MainPagerAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        binding.apply {
            navController.addOnDestinationChangedListener{_,destination,_->
                when(destination.id){
                    R.id.fragmentSplash -> bottomNavigationView.isVisible = false
                    R.id.fragmentIntro -> bottomNavigationView.isVisible = false
                    R.id.fragmentLogin -> bottomNavigationView.isVisible = false
                    R.id.fragmentRegister -> bottomNavigationView.isVisible = false
                    R.id.fragmentForgetPassword -> bottomNavigationView.isVisible = false
                    R.id.commentsFragment -> bottomNavigationView.isVisible = false
                    R.id.imageSliderFragment-> bottomNavigationView.isVisible = false

                    else -> {bottomNavigationView.isVisible = true}
                }



            }
   }


    }

//    override fun onBackPressed() {
//        if (navController.currentBackStackEntry?.destination?.id == R.id.homeFragment &&
//            navController.previousBackStackEntry?.destination?.id == R.id.fragmentSplash ||
//            navController.previousBackStackEntry?.destination?.id == R.id.fragmentLogin
//        ) {
//
//            finish()
//        }
//        super.onBackPressed()
//    }
}