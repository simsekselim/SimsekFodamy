package com.mobillium.simsekfodamy.model



data class Category(
    val id: Int,
    val name: String,
    val language: String,
    val mainCategoryId: Int,
    val image: Image,
    val recipeCount: Int,
    val recipes: List<Recipe>
)