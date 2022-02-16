package com.mobillium.simsekfodamy.presentation.images

import android.os.Bundle
import com.mobillium.domain.model.ImageList
import com.mobillium.simsekfodamy.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageSliderViewModel @Inject constructor() : BaseViewModel() {

    var images: ImageList? = null

    override fun fetchExtras(extras: Bundle) {
        super.fetchExtras(extras)
        images = ImageSliderFragmentArgs.fromBundle(extras).image
    }

    fun exit() {
        popBackStack()
    }
}
