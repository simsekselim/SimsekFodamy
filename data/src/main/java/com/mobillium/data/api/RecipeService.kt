package com.mobillium.data.api

import com.mobillium.data.response.BaseResponse
import com.mobillium.data.response.CategoryResponse
import com.mobillium.data.response.CommentResponse
import com.mobillium.domain.model.Recipe
import com.mobillium.domain.utils.Constants
import retrofit2.http.*

interface RecipeService {

    @GET(Constants.RECIPE)
    suspend fun getRecipe(
        @Path("recipe_id") recipe_id: Int
    ): Recipe

    @GET(Constants.LAST_ADD)
    suspend fun getLastAddedRecipes(
        @Query("page") page: Int
    ): BaseResponse<List<Recipe>>

    @GET(Constants.EDITOR_CHOOSE)
    suspend fun getEditorChoiceRecipes(
        @Query("page") page: Int
    ): BaseResponse<List<Recipe>>

    @GET(Constants.CATEGORY_RECIPES)
    suspend fun getRecipeCategories(
        @Query("page") page: Int
    ): BaseResponse<List<CategoryResponse>>

    @GET(Constants.CATEGORY)
    suspend fun getCategoryRecipes(
        @Path("category_id") categoryId: Int,
        @Query("page") page: Int
    ): BaseResponse<List<Recipe>>

    @GET(Constants.RECIPE_ID)
    suspend fun getRecipeById(
        @Path("recipe_id") recipeId: Int
    ): Recipe

    @GET(Constants.RECIPE_COMMENTS)
    suspend fun getRecipeComments(
        @Path("recipe_id") recipeId: Int,
        @Query("page") page: Int
    ): BaseResponse<List<CommentResponse>>

    @POST(Constants.SEND_COMMENTS)
    suspend fun sendComment(
        @Path("recipe_id") recipeId: Int,
        @Query("text") text: String
    ): CommentResponse

    @POST(Constants.LIKE_RECIPE)
    suspend fun likeRecipe(
        @Path("recipe_id") recipeId: Int
    ): BaseResponse<Any>

    @DELETE(Constants.DISLIKE_RECIPE)
    suspend fun dislikeRecipe(
        @Path("recipe_id") recipeId: Int
    ): BaseResponse<Any>

    @PUT(Constants.EDIT_COMMENTS)
    suspend fun editRecipeComments(
        @Path("recipe_id") recipeID: Int,
        @Path("comment_id") commentID: Int,
        @Query("text") text: String
    ): BaseResponse<Any>

    @DELETE(Constants.DELETE_COMMENTS)
    suspend fun deleteRecipeComments(
        @Path("recipe_id") recipeID: Int,
        @Path("comment_id") commentID: Int
    ): BaseResponse<Any>
}
