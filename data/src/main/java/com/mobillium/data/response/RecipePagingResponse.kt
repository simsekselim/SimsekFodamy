package com.mobillium.data.response

import com.mobillium.domain.model.Pagination

data class RecipePagingResponse(
    val data: List<RecipeResponse>,
    val pagination: PaginationResponse
)
