package com.mobillium.data.remote.response

data class RecipePagingResponse(
    val data: List<RecipeResponse>,
    val pagination: PaginationResponse
)
