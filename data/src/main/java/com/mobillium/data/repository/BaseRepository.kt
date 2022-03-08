package com.mobillium.data.repository

import com.google.gson.Gson
import com.mobillium.data.exceptions.*
import com.mobillium.domain.model.ErrorControl
import retrofit2.HttpException

abstract class BaseRepository {
    suspend fun <T : Any> execute(request: suspend () -> T): T {
        try {
            return request.invoke()
        } catch (ex: Exception) {
            throw parseException(ex)
        }
    }

    open suspend fun <T> fetchFromLocal(cached: suspend () -> T?): T? {
        return try {
            cached.invoke()
        } catch (ex: Exception) {
            throw parseException(ex)
        }
    }

    open suspend fun saveToLocal(store: suspend () -> Unit) {
        try {
            store.invoke()
        } catch (ex: Exception) {
            throw parseException(ex)
        }
    }

    private fun parseException(ex: Exception): Exception {

        return when (ex) {
            is HttpException -> {
                when (ex.response()?.code()) {
                    CODE_UNAUTHORIZIED -> Unauthorized()
                    CODE_AUTHENTICATION -> Authentication()
                    else -> {
                        val response = Gson().fromJson(
                            ex.response()?.errorBody()?.charStream(),
                            ErrorControl::class.java
                        )
                        SimpleHttpException(response.code, response.error)
                    }
                }
            }
            is IndexOutOfBoundsException -> GettingEmptyListItem()
            else -> BaseException(ex.message.toString())
        }
    }

    companion object {
        private const val CODE_AUTHENTICATION = 403
        private const val CODE_UNAUTHORIZIED = 401
    }
}
