package com.mobillium.domain.model

data class Image(
    val url: String,
    val height: Int,
    val width: Int
)

data class ImageList(val images: List<Image>)