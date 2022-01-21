package com.mobillium.domain.model

data class BaseResponse<T>(
    val code: String,
    val message: String,
    val data: T,
    val pagination: Pagination
)
