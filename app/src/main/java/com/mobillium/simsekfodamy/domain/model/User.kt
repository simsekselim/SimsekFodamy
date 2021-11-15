package com.mobillium.simsekfodamy.domain.model



data class User(
    val birthday: Any,
    val cover: Any,
    val cover_image: Any,
    val definition: Any,
    val email: String,
    val facebook_url: Any,
    val favorites_count: Int,
    val followed_count: Int,
    val following_count: Int,
    val gender: Any,
    val id: Int,
    val image: Any,
    val instagram_url: Any,
    val is_banned: Int,
    val is_following: Boolean,
    val is_top_user_choice: Boolean,
    val is_trusted: Int,
    val language: String,
    val likes_count: Int,
    val name: String,
    val phone: Any,
    val recipe_count: Int,
    val surname: String,
    val tckn: Any,
    val top_user: Any,
    val twitter_url: Any,
    val username: String,
    val youtube_url: Any
)
