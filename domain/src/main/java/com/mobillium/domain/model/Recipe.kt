package com.mobillium.domain.model

import android.media.Image

data class Recipe(
    val category: Category,
    val comment_count: Int,
    val definition: String,
    val difference: String,
    val directions: String,
    val id: Int,
    val images: List<com.mobillium.domain.model.Image>,
    val ingredients: String,
    val is_editor_choice: Boolean,
    val is_favorited: Boolean,
    val is_liked: Boolean,
    val like_count: Int,
    val number_of_person: NumberOfPerson,
    val time_of_recipe: TimeOfRecipe,
    val title: String,
    val user: User
)