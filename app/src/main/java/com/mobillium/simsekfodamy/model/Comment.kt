package com.mobillium.simsekfodamy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Comment(
    val id: Int,
    val text: String,
    val difference: String,
    val user: User
) : Parcelable