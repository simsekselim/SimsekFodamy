package com.mobillium.data.utils

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.mobillium.data.local.dao.RecipeDao
import com.mobillium.data.local.dao.RemoteKeysDao
import com.mobillium.data.local.model.CategoryLocal
import com.mobillium.data.local.model.RemoteKeyCategory
import com.mobillium.data.mapper.toLocalModel
import com.mobillium.data.remote.api.RecipeService

@ExperimentalPagingApi
class RemoteMediatorCategories(
    private val recipeService: RecipeService,
    private val recipeDao: RecipeDao,
    private val remoteKeysDao: RemoteKeysDao
) : RemoteMediator<Int, CategoryLocal>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CategoryLocal>
    ): MediatorResult {
        return try {

            val utils = RemoteMediatorUtils<Int, CategoryLocal>(
                remoteKeysDao = remoteKeysDao,
                keyType = PagingKeyType.BY_CATEGORY
            )
            val currentPage = when (val keyData = utils.getPageKey(loadType, state)) {
                is MediatorResult.Success -> {
                    return keyData
                }
                else -> {
                    keyData as Int
                }
            }
            val response = recipeService.getRecipeCategories(currentPage)
            val endOfPagination = response.data.isEmpty()

            val prevPage = if (currentPage == STARTING_INDEX) null else currentPage - 1
            val nextPage = if (endOfPagination) null else currentPage + 1

            if (loadType == LoadType.REFRESH) {
                remoteKeysDao.deleteCategoryRecipesKeys()
            }

            val keys = response.data.map {
                RemoteKeyCategory(
                    id = it.id,
                    prev = prevPage,
                    next = nextPage
                )
            }
            remoteKeysDao.insertCategoryRecipesRemoteKeys(keys)

            val filteredCategories = response.data.filter {
                it.recipes?.size!! > 0
            }.map { it.toLocalModel() }

            recipeDao.insertCategories(filteredCategories)
            MediatorResult.Success(endOfPaginationReached = endOfPagination)
        } catch (ex: Exception) {
            MediatorResult.Error(ex)
        }
    }

    companion object {
        private const val STARTING_INDEX = 1
    }
}
