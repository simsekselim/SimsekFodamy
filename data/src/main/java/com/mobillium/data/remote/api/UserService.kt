package com.mobillium.data.remote.api

import com.mobillium.data.remote.response.CommonResponse
import com.mobillium.data.remote.response.LoginResponse
import com.mobillium.data.remote.response.UserResponse
import com.mobillium.domain.utils.Constants
import retrofit2.http.*

interface UserService {
    @FormUrlEncoded
    @POST(Constants.LOGIN_URL)
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): LoginResponse

    @FormUrlEncoded
    @POST(Constants.REGISTER_URL)
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @POST(Constants.FORGOT_URL)
    suspend fun forgotPassword(
        @Query("email") email: String
    ): LoginResponse

    @POST(Constants.LOGOUT_URL)
    suspend fun logout(): CommonResponse

    @GET(Constants.USER)
    suspend fun getUser(
        @Path("user_id") userId: Int
    ): UserResponse

    @POST(Constants.FOLLOW_USER)
    suspend fun followUser(
        @Path("followedId") followedId: Int
    ): CommonResponse

    @DELETE(Constants.UNFOLLOW_USER)
    suspend fun unfollowUser(
        @Path("followedId") followedId: Int
    ): CommonResponse
}