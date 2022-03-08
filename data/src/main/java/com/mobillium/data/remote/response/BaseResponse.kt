package com.mobillium.data.remote.response

data class BaseResponse<T>(
    val data: T,
    val pagination: PaginationResponse
)
