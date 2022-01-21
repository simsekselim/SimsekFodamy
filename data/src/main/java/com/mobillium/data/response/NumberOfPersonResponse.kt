package com.mobillium.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NumberOfPersonResponse(
    val id: Int,
    val text: String
) : Parcelable
