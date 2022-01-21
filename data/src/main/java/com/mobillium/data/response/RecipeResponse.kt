package com.mobillium.data.response

data class RecipeResponse(
    val categoryResponse: CategoryResponse,
    val comment_count: Int,
    val definition: String,
    val difference: String,
    val directions: String,
    val id: Int,
    val images: List<Image>,
    val ingredients: String,
    val is_approved: Boolean,
    val is_editor_choice: Boolean,
    val is_favorited: Boolean,
    val is_liked: Boolean,
    val is_owner: Boolean,
    val language: String,
    val like_count: Int,
    val number_of_favorite_count: Int,
    val number_of_personResponse: NumberOfPersonResponse,
    val time_of_recipeResponse: TimeOfRecipeResponse,
    val title: String,
    val user: User,
    val view_count: Int,
    val youtube_url: String
)
