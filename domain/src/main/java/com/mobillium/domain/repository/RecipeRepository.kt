package com.mobillium.domain.repository

import androidx.paging.PagingData
import com.mobillium.domain.model.BaseResponse
import com.mobillium.domain.model.Category
import com.mobillium.domain.model.Recipe
import com.mobillium.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import org.w3c.dom.Comment

interface RecipeRepository {
    suspend fun getRecipe(id: Int): com.mobillium.domain.utils.Result<Recipe>
    fun getLastAddedRecipes(): Flow<PagingData<Recipe>>
    fun getEditorChoiceRecipes(): Flow<PagingData<Recipe>>
    fun getRecipeCategories(): Flow<PagingData<Category>>
    fun getCategoryRecipes(categoryId: Int): Flow<PagingData<Recipe>>
    suspend fun getRecipeByID(recipeId: Int): Result<Recipe>
    fun getRecipeComments(recipeId: Int): Flow<PagingData<Comment>>
    suspend fun getFirstComment(recipeId: Int): com.mobillium.domain.utils.Result<Comment>
    suspend fun sendComment(recipeId: Int, text: String): com.mobillium.domain.utils.Result<Comment>
    suspend fun likeRecipe(recipeId: Int): com.mobillium.domain.utils.Result<BaseResponse<Any>>
    suspend fun dislikeRecipe(recipeId: Int): com.mobillium.domain.utils.Result<BaseResponse<Any>>
    suspend fun editRecipeComments(
        recipeId: Int,
        comment: Int,
        text: String
    ): com.mobillium.domain.utils.Result<BaseResponse<Any>>
    suspend fun deleteRecipeComments(recipeId: Int, comment: Int): com.mobillium.domain.utils.Result<BaseResponse<Any>>
}
