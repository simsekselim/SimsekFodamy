package com.mobillium.simsekfodamy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val email: String,
    val favorites_count: Int,
    val followed_count: Int,
    val following_count: Int,
    val id: Int,
    val image: Image?,
    val is_following: Boolean,
    val is_top_user_choice: Boolean,
    val likes_count: Int,
    val recipe_count: Int,
    val username: String

) : Parcelable
