package com.mobillium.simsekfodamy.presentation.loginflow.intro

import com.mobillium.simsekfodamy.base.BaseViewModel

class IntroViewModel : BaseViewModel() {
    var titlesList = mutableListOf<String>()
    var descList = mutableListOf<String>()
    var imagesList = mutableListOf<Int>()

    fun cancel() {
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
