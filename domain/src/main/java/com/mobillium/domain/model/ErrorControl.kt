package com.mobillium.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ErrorControl(
    val code: String,
    val error: String
) : Parcelable
