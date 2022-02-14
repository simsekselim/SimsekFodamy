package com.mobillium.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Login(
    var token: String,
    var user: User
) : Parcelable
