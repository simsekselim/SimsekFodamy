package com.mobillium.domain.model

data class Comment(
    val id: Int,
    val text: String,
    val difference: String,
    val user: User
)
