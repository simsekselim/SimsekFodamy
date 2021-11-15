package com.mobillium.simsekfodamy.domain.model

import com.mobillium.simsekfodamy.domain.model.Constants
import com.mobillium.simsekfodamy.domain.model.LoginRequest
import com.mobillium.simsekfodamy.domain.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST(Constants.LOGIN_URL)
    fun login(@Body request: LoginRequest) : Call<LoginResponse>
}

