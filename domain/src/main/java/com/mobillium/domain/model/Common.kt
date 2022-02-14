package com.mobillium.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Common(
    val code: String,
    val message: String,
    val error: String
) : Parcelable
