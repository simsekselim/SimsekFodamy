package com.mobillium.simsekfodamy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TimeOfRecipe(
    val id: Int,
    val text: String
) : Parcelable