package com.mobillium.data.api

import com.mobillium.data.response.*
import com.mobillium.domain.model.Recipe
import com.mobillium.domain.utils.Constants
import retrofit2.http.*

interface RecipeService {

    @GET(Constants.RECIPE)
    suspend fun getRecipe(
        @Path("recipe_id") recipe_id: Int
    ): RecipeResponse

    @GET(Constants.LAST_ADD)
    suspend fun getLastAddedRecipes(
        @Query("page") page: Int
    ): RecipePagingResponse

    @GET(Constants.EDITOR_CHOOSE)
    suspend fun getEditorChoiceRecipes(
        @Query("page") page: Int
    ): RecipePagingResponse

    @GET(Constants.CATEGORY_RECIPES)
    suspend fun getRecipeCategories(
        @Query("page") page: Int
    ): CategoryPagingResponse

    @GET(Constants.CATEGORY)
    suspend fun getCategoryRecipes(
        @Path("category_id") categoryId: Int,
        @Query("page") page: Int
    ): RecipePagingResponse

    @GET(Constants.RECIPE_ID)
    suspend fun getRecipeById(
        @Path("recipe_id") recipeId: Int
    ): RecipeResponse

    @GET(Constants.RECIPE_COMMENTS)
    suspend fun getRecipeComments(
        @Path("recipe_id") recipeId: Int,
        @Query("page") page: Int
    ): CommentPagingResponse

    @POST(Constants.SEND_COMMENTS)
    suspend fun sendComment(
        @Path("recipe_id") recipeId: Int,
        @Query("text") text: String
    ): CommentResponse

    @POST(Constants.LIKE_RECIPE)
    suspend fun likeRecipe(
        @Path("recipe_id") recipeId: Int
    ): CommonResponse

    @DELETE(Constants.DISLIKE_RECIPE)
    suspend fun dislikeRecipe(
        @Path("recipe_id") recipeId: Int
    ): CommonResponse

    @PUT(Constants.EDIT_COMMENTS)
    suspend fun editRecipeComments(
        @Path("recipe_id") recipeID: Int,
        @Path("comment_id") commentID: Int,
        @Query("text") text: String
    ): CommonResponse

    @DELETE(Constants.DELETE_COMMENTS)
    suspend fun deleteRecipeComments(
        @Path("recipe_id") recipeID: Int,
        @Path("comment_id") commentID: Int
    ): CommonResponse
}
