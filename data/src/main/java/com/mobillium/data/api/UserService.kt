package com.mobillium.data.api

import com.mobillium.data.response.BaseResponse
import com.mobillium.data.response.LoginResponse
import com.mobillium.data.response.User
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
    ): Any

    @POST(Constants.LOGOUT_URL)
    suspend fun logout(): BaseResponse<Any>

    @GET(Constants.USER)
    suspend fun getUser(
        @Path("user_id") userId: Int
    ): User

    @POST(Constants.FOLLOW_USER)
    suspend fun followUser(
        @Path("followedId") followedId: Int
    ): BaseResponse<Any>

    @DELETE(Constants.UNFOLLOW_USER)
    suspend fun unfollowUser(
        @Path("followedId") followedId: Int
    ): BaseResponse<Any>
}
