package com.mobillium.data.remote.datasource

import com.mobillium.data.remote.api.RecipeService
import com.mobillium.data.remote.response.*
import javax.inject.Inject

class RemoteRecipeDataSource @Inject constructor(
    private val recipeService: RecipeService
) {
    suspend fun getRecipe(recipeId: Int): RecipeResponse {
        return recipeService.getRecipe(recipeId)
    }

    suspend fun getLastAddedRecipes(page: Int): RecipePagingResponse {
        return recipeService.getLastAddedRecipes(page)
    }

    suspend fun getEditorChoiceRecipes(page: Int): RecipePagingResponse {
        return recipeService.getEditorChoiceRecipes(page)
    }

    suspend fun getRecipeCategories(page: Int): CategoryPagingResponse {
        return recipeService.getRecipeCategories(page)
    }

    suspend fun getCategoryRecipes(categoryId: Int, page: Int): RecipePagingResponse {
        return recipeService.getCategoryRecipes(categoryId, page)
    }

    suspend fun getRecipeComments(recipeId: Int, page: Int): CommentPagingResponse {
        return recipeService.getRecipeComments(recipeId, page)
    }

    suspend fun sendComment(recipeId: Int, text: String): CommentResponse {
        return recipeService.sendComment(recipeId, text)
    }

    suspend fun likeRecipe(recipeId: Int): CommonResponse {
        return recipeService.likeRecipe(recipeId)
    }

    suspend fun dislikeRecipe(recipeId: Int): CommonResponse {
        return recipeService.dislikeRecipe(recipeId)
    }

    suspend fun editRecipeComments(recipeID: Int, commentID: Int, text: String): CommonResponse {
        return recipeService.editRecipeComments(recipeID, commentID, text)
    }

    suspend fun deleteRecipeComments(recipeID: Int, commentID: Int): CommonResponse {
        return recipeService.deleteRecipeComments(recipeID, commentID)
    }
}
