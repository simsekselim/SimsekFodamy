package com.mobillium.simsekfodamy.presentation.images

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentImageSliderBinding
import com.mobillium.simsekfodamy.model.Image
import com.mobillium.simsekfodamy.model.ImageList
import com.mobillium.simsekfodamy.presentation.images.adapter.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageSliderFragment() :
    BaseFragment<ImageSliderViewModel, FragmentImageSliderBinding>(
        R.layout.fragment_image_slider,
        ImageSliderViewModel::class.java
    ) {

    private val args: ImageSliderFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val images: ImageList = args.image
        val populatedImages = mutableListOf<Image>()
        if (images.images.size == 1) {
            val image = images.images[0]
            for (i in 0 until 4) {
                populatedImages.add(image)
            }
        }

        val imageAdapter = ImageAdapter(ImageList(populatedImages).images)
        binding.viewPagerImages.adapter = imageAdapter
        binding.indicator.setViewPager2(binding.viewPagerImages)
    }
}
