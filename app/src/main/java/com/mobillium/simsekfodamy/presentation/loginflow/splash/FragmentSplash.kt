package com.mobillium.simsekfodamy.presentation.loginflow.splash

import android.os.Bundle
import android.view.View
import com.mobillium.data.utils.PreferencesManager
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FragmentSplash : BaseFragment<SplashViewModel, FragmentSplashBinding>(
    R.layout.fragment_splash,
    SplashViewModel::class.java
) {
    @Inject lateinit var preferences: PreferencesManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {

            delay(2500)

            if (preferences.first()) {
                viewModel.toIntro()
                preferences.updateFirst(false)
            } else {
                viewModel.toHome()
            }
        }
    }
}
