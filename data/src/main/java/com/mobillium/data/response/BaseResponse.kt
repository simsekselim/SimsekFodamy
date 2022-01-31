package com.mobillium.data.response

data class BaseResponse<T>(
    val code: String,
    val message: String,
    val data: T,
    val pagination: PaginationResponse
)
