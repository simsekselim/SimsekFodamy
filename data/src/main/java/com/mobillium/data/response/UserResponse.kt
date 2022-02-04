package com.mobillium.data.response

data class UserResponse(
    val cover: String?,
    val definition: String?,
    val facebook_url: String?,
    val favorites_count: Int?,
    val followed_count: Int?,
    val following_count: Int?,
    val id: Int,
    val image: ImageResponse?,
    val instagram_url: String?,
    val is_following: Boolean,
    val is_top_user_choice: Boolean,
    val is_trusted: Int?,
    val language: String?,
    val likes_count: Int?,
    val name: String?,
    val recipe_count: Int?,
    val surname: String?,
    val twitter_url: String?,
    val username: String?,
    val youtube_url: String?
)
