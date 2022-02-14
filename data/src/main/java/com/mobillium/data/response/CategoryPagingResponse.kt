package com.mobillium.data.response

data class CategoryPagingResponse(
    val data: List<CategoryResponse>,
    val pagination: PaginationResponse
)
