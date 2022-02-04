package com.mobillium.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    val url: String,
    val height: Int,
    val width: Int
) : Parcelable

