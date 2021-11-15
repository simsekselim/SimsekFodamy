package com.mobillium.simsekfodamy.domain.model

import com.google.gson.annotations.SerializedName
import com.mobillium.simsekfodamy.domain.model.User

class LoginResponse(
    @SerializedName("token")
    var token: String,
    @SerializedName("user")
    var user : User
) {
}

