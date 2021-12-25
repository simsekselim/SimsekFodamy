package com.mobillium.simsekfodamy.presentation.loginflow.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentMainBinding
import com.mobillium.simsekfodamy.databinding.FragmentSplashBinding
import com.mobillium.simsekfodamy.presentation.main.MainViewModel
import com.mobillium.simsekfodamy.utils.PreferencesManager
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        CoroutineScope(Dispatchers.Main).launch {

            delay(2500)

            if (preferences.isFirst().isNullOrBlank()){
                findNavController().navigate(R.id.fragmentIntro)
            }else{
                findNavController().navigate(FragmentSplashDirections.actionFragmentSplashToHome())
            }

        }

    }


}