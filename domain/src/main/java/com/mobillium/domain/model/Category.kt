package com.mobillium.domain.model

data class Category(
    val id: Int,
    val name: String,
    val image: Image,
    val recipes: List<Recipe>
)
