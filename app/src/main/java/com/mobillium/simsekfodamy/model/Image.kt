package com.mobillium.simsekfodamy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val width: Int,
    val height: Int,
    val key: String,
    val order: Int,
    val url: String
) : Parcelable

@Parcelize
data class ImageList(val images: List<Image>) : Parcelable
