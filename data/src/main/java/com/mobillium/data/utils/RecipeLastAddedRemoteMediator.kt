package com.mobillium.data.utils

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.mobillium.data.local.dao.RecipeDao
import com.mobillium.data.local.dao.RemoteKeysDao
import com.mobillium.data.local.model.RecipeLocal
import com.mobillium.data.local.model.RemoteKeyLast
import com.mobillium.data.mapper.toLocalModel
import com.mobillium.data.remote.api.RecipeService

@ExperimentalPagingApi
class RecipeLastAddedRemoteMediator(
    private val recipeService: RecipeService,
    private val recipeDao: RecipeDao,
    private val remoteKeysDao: RemoteKeysDao,
) : RemoteMediator<Int, RecipeLocal>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RecipeLocal>
    ): MediatorResult {

        return try {
            val utils = RemoteMediatorUtils<Int, RecipeLocal>(
                remoteKeysDao = remoteKeysDao,
                keyType = PagingKeyType.LAST_ADDED
            )
            val currentPage =
                when (val keyData = utils.getPageKey(loadType, state)) {
                    is MediatorResult.Success -> {
                        return keyData
                    }
                    else -> {
                        keyData as Int
                    }
                }

            val response = recipeService.getLastAddedRecipes(currentPage)
            val endOfPagination = response.data.isEmpty()

            val prevPage = if (currentPage == STARTING_INDEX) null else currentPage - 1
            val nextPage = if (endOfPagination) null else currentPage + 1

            if (loadType == LoadType.REFRESH) {
                remoteKeysDao.deleteLastKeys()
            }
            val keys = response.data.map {
                RemoteKeyLast(
                    id = it.id,
                    prev = prevPage,
                    next = nextPage
                )
            }
            remoteKeysDao.insertLasAtAddedRemoteKeys(keys)
            recipeDao.insertRecipes(response.data.map { it.toLocalModel(is_last_added = true) })
            MediatorResult.Success(endOfPaginationReached = endOfPagination)
        } catch (ex: Exception) {
            MediatorResult.Error(ex)
        }
    }

    companion object {
        private const val STARTING_INDEX = 1
    }
}
