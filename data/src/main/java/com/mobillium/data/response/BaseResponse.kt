package com.mobillium.data.response

data class BaseResponse<T>(
    val data: T,
    val pagination: PaginationResponse
)
