package com.mobillium.simsekfodamy.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mobillium.simsekfodamy.api.RecipeService
import com.mobillium.simsekfodamy.model.Recipe
import retrofit2.HttpException
import java.io.IOException

class RecipePagingFactory(
    private val api: RecipeService,
    private val key: String,
) : PagingSource<Int, Recipe>() {
    companion object {
        const val STARTING_PAGE_INDEX = 1
        const val GET_LAST = "GET_LAST"
        const val GET_EDITOR_CHOICE = "GET_EDITOR_CHOICE"
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response =
                when (key) {
                    GET_LAST -> {
                        api.getLastAddedRecipes(position)
                    }
                    GET_EDITOR_CHOICE -> {
                        api.getEditorChoiceRecipes(position)
                    }

                    else -> {
                        api.getLastAddedRecipes(position)
                    }
                }
            val recipes = response.data
            LoadResult.Page(
                data = recipes,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (recipes.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        TODO("Not yet implemented")
    }

}
