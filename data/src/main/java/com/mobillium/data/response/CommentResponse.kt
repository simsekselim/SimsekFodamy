package com.mobillium.data.response

data class CommentResponse(
    val id: Int,
    val text: String,
    val difference: String,
    val user: User
)
