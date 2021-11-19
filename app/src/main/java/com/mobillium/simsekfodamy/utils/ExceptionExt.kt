package com.mobillium.simsekfodamy

import com.google.gson.Gson
import com.mobillium.simsekfodamy.utils.ErrorResponse
import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketException

fun Exception.handleHttpException(): String {
    return when (this) {
        is HttpException -> Gson().fromJson(this.response()?.errorBody()?.string(), ErrorResponse::class.java).error
        is SocketException -> "Please check Internet connection."
        else -> this.message.toString()
    }
}
