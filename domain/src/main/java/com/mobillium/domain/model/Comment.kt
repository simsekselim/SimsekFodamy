package com.mobillium.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
    val id: Int,
    val text: String,
    val difference: String,
    val user: User
):Parcelable
