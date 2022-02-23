package com.mobillium.data.remote.response

data class PaginationResponse(
    val current_page: Int,
    val first_item: Int,
    val last_item: Int,
    val last_page: Int,
    val per_page: Int,
    val total: Int
)
