package com.mobillium.simsekfodamy.api

import com.mobillium.simsekfodamy.response.BaseResponse
import com.mobillium.simsekfodamy.model.Category
import com.mobillium.simsekfodamy.model.Comment
import com.mobillium.simsekfodamy.model.Recipe
import com.mobillium.simsekfodamy.utils.Constants
import retrofit2.http.*

interface RecipeService {

    //Path : This annotation implies that the passed parameter will be swapped in the endpoint path
    //Query : This annotation represents any query key value pair to be sent along with the network request

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
    ): BaseResponse<List<Category>>

    @GET(Constants.CATEGORY)
    suspend fun getCategoryRecipes(
        @Path("category_id") categoryId: Int,
        @Query("page") page: Int
    ): BaseResponse<List<Recipe>>


    @GET("recipe/{recipe_id}")
    suspend fun getRecipeById(
        @Path("recipe_id") recipeId: Int
    ): Recipe

    @GET("recipe/{recipe_id}/comment")
    suspend fun getRecipeComments(
        @Path("recipe_id") recipeId: Int,
        @Query("page") page: Int
    ): BaseResponse<List<Comment>>

    @POST("recipe/{recipe_id}/comment")
    suspend fun sendComment(
        @Path("recipe_id") recipeId: Int,
        @Query("text") text: String
    ): Comment

    @POST("recipe/{recipe_id}/like")
    suspend fun likeRecipe(
        @Path("recipe_id") recipeId: Int
    ): BaseResponse<Any>

    @DELETE("recipe/{recipe_id}/like")
    suspend fun dislikeRecipe(
        @Path("recipe_id") recipeId: Int
    ): BaseResponse<Any>

}