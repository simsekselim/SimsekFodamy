package com.mobillium.data.response

data class CommentPagingResponse(
    val data: List<CommentResponse>,
    val pagination: PaginationResponse
)
