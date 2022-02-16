package com.mobillium.simsekfodamy.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mobillium.domain.model.Category
import com.mobillium.domain.repository.RecipeRepository
import retrofit2.HttpException
import java.io.IOException

class CategoryPagingFactory(
    private val recipeRepository: RecipeRepository
) : PagingSource<Int, Category>() {
    companion object {
        const val NOTE_STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Category> {
        val position = params.key ?: NOTE_STARTING_PAGE_INDEX
        return try {
            val response = recipeRepository.getRecipeCategories(position)

            val recipes = response ?: emptyList()
            LoadResult.Page(
                data = recipes,
                prevKey = if (position == NOTE_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (recipes.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Category>): Int? {
        TODO("Not yet implemented")
    }
}
