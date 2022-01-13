package com.mobillium.simsekfodamy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pagination(
    val current_page: Int,
    val first_item: Int,
    val last_item: Int,
    val last_page: Int,
    val per_page: Int,
    val total: Int
) : Parcelable
