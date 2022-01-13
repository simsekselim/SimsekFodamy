package com.mobillium.simsekfodamy.presentation.loginflow.intro

import android.content.SharedPreferences
import android.provider.Settings.Secure.getString
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseViewModel

class IntroViewModel : BaseViewModel(){
     var titlesList = mutableListOf<String>()
     var descList = mutableListOf<String>()
     var imagesList = mutableListOf<Int>()

     fun cancel(){
          navigate(FragmentIntroDirections.actionFragmentIntroToFragmentLogin())
     }

     fun addToList(
          title: String,
          description: String,
          image: Int
     ) {
          titlesList.add(title)
          descList.add(description)
          imagesList.add(image)
     }



}
