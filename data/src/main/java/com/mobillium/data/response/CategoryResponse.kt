package com.mobillium.data.response

data class CategoryResponse(
    val id: Int,
    val name: String,
    val language: String,
    val mainCategoryId: Int,
    val image: Image,
    val recipeCount: Int,
    val recipes: List<RecipeResponse>
)
