package com.mobillium.data.response

data class Image(
    val width: Int,
    val height: Int,
    val key: String,
    val order: Int,
    val url: String
)

data class ImageList(val images: List<Image>)
