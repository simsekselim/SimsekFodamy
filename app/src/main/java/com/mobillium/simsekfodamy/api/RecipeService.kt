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



}