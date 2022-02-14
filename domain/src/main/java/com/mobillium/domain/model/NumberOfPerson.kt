package com.mobillium.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NumberOfPerson(
    val id: Int,
    val text: String
) : Parcelable
