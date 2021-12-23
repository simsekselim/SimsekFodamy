package com.mobillium.simsekfodamy.api

import com.mobillium.simsekfodamy.model.User
import com.mobillium.simsekfodamy.response.BaseResponse
import com.mobillium.simsekfodamy.response.LoginResponse
import com.mobillium.simsekfodamy.utils.Constants
import retrofit2.http.*

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

    @GET("user/{user_id}")
    suspend fun getUser(
        @Path("user_id") userId: Int
    ): User

    @POST("user/{followedId}/following")
    suspend fun followUser(
        @Path("followedId") followedId: Int
    ): BaseResponse<Any>

    @DELETE("user/{followedId}/following")
    suspend fun unfollowUser(
        @Path("followedId") followedId: Int
    ): BaseResponse<Any>




}
