package com.mobillium.simsekfodamy.presentation

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_SimsekFodamy)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        binding.apply {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.fragmentSplash -> bottomNavigationView.isVisible = false
                    R.id.fragmentIntro -> bottomNavigationView.isVisible = false
                    R.id.fragmentLogin -> bottomNavigationView.isVisible = false
                    R.id.fragmentRegister -> bottomNavigationView.isVisible = false
                    R.id.fragmentForgetPassword -> bottomNavigationView.isVisible = false
                    R.id.commentsFragment -> bottomNavigationView.isVisible = false
                    R.id.imageSliderFragment -> bottomNavigationView.isVisible = false
                    R.id.editCommentFragment -> bottomNavigationView.isVisible = false
                    R.id.loginWarningDialog -> bottomNavigationView.isVisible = false

                    else -> {
                        bottomNavigationView.isVisible = true
                    }
                }
            }
        }
    }

    private fun loading() {
        dialog = Dialog(this)
        dialog!!.setContentView(R.layout.loading)
        dialog!!.setCancelable(true)
        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun dismissDialog() {
        dialog?.dismiss()
    }

    fun showDialog() {
        if (dialog == null) {
            loading()
        }
        dialog?.show()
    }
}
