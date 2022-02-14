package com.mobillium.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TimeOfRecipe(
    val id: Int,
    val text: String
) : Parcelable
