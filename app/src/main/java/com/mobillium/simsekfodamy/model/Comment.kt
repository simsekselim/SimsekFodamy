package com.mobillium.simsekfodamy.model



data class Comment(
    val id: Int,
    val text: String,
    val difference: String,
    val user: User
)