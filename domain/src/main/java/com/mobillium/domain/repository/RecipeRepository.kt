package com.mobillium.domain.repository

import com.mobillium.domain.model.Category
import com.mobillium.domain.model.Comment
import com.mobillium.domain.model.Common
import com.mobillium.domain.model.Recipe

interface RecipeRepository {
    suspend fun getRecipe(id: Int): Recipe
    suspend fun getLastAddedRecipes(page: Int = 1): List<Recipe>
    suspend fun getEditorChoiceRecipes(page: Int = 1): List<Recipe>
    suspend fun getRecipeCategories(page: Int = 1): List<Category>
    suspend fun getCategoryRecipes(categoryId: Int, page: Int = 1): List<Recipe>
    suspend fun getRecipeComments(recipeId: Int, page: Int = 1): List<Comment>
    suspend fun getFirstComment(recipeId: Int): Comment
    suspend fun sendComment(recipeId: Int, text: String)
    suspend fun likeRecipe(recipeId: Int): Common
    suspend fun dislikeRecipe(recipeId: Int): Common
    suspend fun editRecipeComments(
        recipeId: Int,
        comment: Int,
        text: String
    )

    suspend fun deleteRecipeComments(recipeId: Int, comment: Int)
}
