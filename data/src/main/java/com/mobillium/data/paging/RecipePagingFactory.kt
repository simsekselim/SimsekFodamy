package com.mobillium.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mobillium.domain.model.Recipe
import com.mobillium.domain.repository.RecipeRepository
import retrofit2.HttpException
import java.io.IOException

class RecipePagingFactory(
    private val recipeRepository: RecipeRepository,
    private val key: String,
    private val categoryId: Int?
) : PagingSource<Int, Recipe>() {
    companion object {
        const val STARTING_PAGE_INDEX = 1
        const val GET_LAST = "GET_LAST"
        const val GET_CATEGORY_RECIPES = "GET_CATEGORY_RECIPES"
        const val GET_EDITOR_CHOICE = "GET_EDITOR_CHOICE"
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response =
                when (key) {
                    GET_LAST -> {
                        recipeRepository.getLastAddedRecipes(position)
                    }
                    GET_EDITOR_CHOICE -> {
                        recipeRepository.getEditorChoiceRecipes(position)
                    }
                    GET_CATEGORY_RECIPES -> {
                        recipeRepository.getCategoryRecipes(categoryId!!, position)
                    }
                    else -> {
                        recipeRepository.getLastAddedRecipes(position)
                    }
                }
            val recipes = response ?: emptyList()
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
