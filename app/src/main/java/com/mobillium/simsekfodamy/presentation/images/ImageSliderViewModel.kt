package com.mobillium.simsekfodamy.presentation.images

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.model.ImageList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageSliderViewModel @Inject constructor(
    private val state: SavedStateHandle
) : BaseViewModel() {

    val images = MutableLiveData(state.get<ImageList>(IMAGES))

    fun exit() {
        popBackStack()
    }
    companion object {
        const val IMAGES = "images"

    }
}
