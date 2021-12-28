package com.mobillium.simsekfodamy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Int,
    val name: String,
    val language: String,
    val mainCategoryId: Int,
    val image: Image,
    val recipeCount: Int,
    val recipes: List<Recipe>
) : Parcelable
