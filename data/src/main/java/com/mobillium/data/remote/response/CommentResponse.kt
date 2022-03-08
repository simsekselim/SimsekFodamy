package com.mobillium.data.remote.response

data class CommentResponse(
    val id: Int,
    val text: String,
    val difference: String,
    val user: UserResponse
)
