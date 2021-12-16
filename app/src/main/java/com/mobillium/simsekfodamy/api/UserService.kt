package com.mobillium.simsekfodamy.api

import com.mobillium.simsekfodamy.response.BaseResponse
import com.mobillium.simsekfodamy.response.LoginResponse
import com.mobillium.simsekfodamy.utils.Constants
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @FormUrlEncoded
    @POST("${Constants.LOGIN_URL}")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): LoginResponse

    @FormUrlEncoded
    @POST("${Constants.REGISTER_URL}")
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @POST("auth/forgot")
    suspend fun forgotPassword(
        @Query("email") email: String
    ): Any

    @POST("auth/logout")
    suspend fun logout(): BaseResponse<Any>




}
