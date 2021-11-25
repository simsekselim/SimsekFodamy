package com.mobillium.simsekfodamy.model

import com.google.gson.annotations.SerializedName
import com.mobillium.simsekfodamy.model.User

class LoginResponse(
    @SerializedName("token")
    var token: String,
    @SerializedName("user")
    var user : User,
)