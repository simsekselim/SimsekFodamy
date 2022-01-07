package com.mobillium.simsekfodamy.response

import com.mobillium.simsekfodamy.model.Pagination

data class BaseResponse<T>(
    val status: Status,
    val code: String,
    val message: String,
    val data: T,
    val pagination: Pagination
)
enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    REDIRECT
}