package com.mobillium.data.remote.response

import com.mobillium.domain.model.Image

data class ImageResponse(
    val width: Int,
    val height: Int,
    val key: String,
    val order: Int,
    val url: String
)

data class ImageList(val images: List<Image>)
