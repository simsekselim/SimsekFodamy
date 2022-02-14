package com.mobillium.data.response

data class RecipePagingResponse(
    val data: List<RecipeResponse>,
    val pagination: PaginationResponse
)
