package com.mobillium.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    val id: Int,
    val name: String,
    val image: Image?,
    val recipes: List<Recipe>?
) : Parcelable
