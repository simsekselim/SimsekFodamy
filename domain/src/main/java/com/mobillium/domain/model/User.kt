package com.mobillium.domain.model

data class User(
    val followed_count: Int,
    val following_count: Int,
    val favorites_count: Int,
    val id: Int,
    val image: Image?,
    val is_following: Boolean,
    val likes_count: Int,
    val recipe_count: Int,
    val name: String,
    val username: String

)
