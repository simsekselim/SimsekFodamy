package com.mobillium.simsekfodamy.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.mobillium.simsekfodamy.model.User
import kotlinx.parcelize.Parcelize

@Parcelize
class LoginResponse(
    var token: String,
    var user : User
) :Parcelable
