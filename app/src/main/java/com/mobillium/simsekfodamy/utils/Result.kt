package com.mobillium.simsekfodamy.utils

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val response: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}
