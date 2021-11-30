package com.mobillium.simsekfodamy.model

data class Category(
    val id: Int,
    val image: Image,
    val language: String,
    val main_category_id: Any,
    val name: String
)