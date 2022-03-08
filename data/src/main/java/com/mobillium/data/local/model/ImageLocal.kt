package com.mobillium.data.local.model

data class ImageLocal(
    val url: String,
    val height: Int,
    val width: Int
) {
    companion object {
        val EMPTY = ImageLocal("", 0, 0)
    }
}
