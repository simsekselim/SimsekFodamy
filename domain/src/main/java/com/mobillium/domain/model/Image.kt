package com.mobillium.domain.model

data class Image(
    val url: String
)

data class ImageList(val images: List<Image>)
