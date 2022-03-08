package com.mobillium.data.utils

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.mobillium.data.local.dao.RemoteKeysDao
import com.mobillium.data.local.model.CategoryLocal
import com.mobillium.data.local.model.CommentLocal
import com.mobillium.data.local.model.RecipeLocal

@ExperimentalPagingApi
class RemoteMediatorUtils<Key : Any, Value : Any>(
    private val remoteKeysDao: RemoteKeysDao,
    private val keyType: PagingKeyType
) {

    private fun getKey(data: Value): RemoteKey {
        return when (keyType) {
            PagingKeyType.EDITOR_CHOICE -> {
                remoteKeysDao.getEditorRemoteKeys((data as RecipeLocal).id)
            }
            PagingKeyType.LAST_ADDED -> {
                remoteKeysDao.getLastAddedRemoteKeys((data as RecipeLocal).id)
            }
            PagingKeyType.BY_CATEGORY -> {
                remoteKeysDao.getCategoryRemoteKeys((data as CategoryLocal).id)
            }
            PagingKeyType.COMMENT -> {
                remoteKeysDao.getCommentsRemoteKey((data as CommentLocal).id)
            }
        }
    }

    fun getPageKey(loadType: LoadType, state: PagingState<Key, Value>): Any {
        return when (loadType) {
            LoadType.REFRESH -> INITIAL_LOAD
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                remoteKeys?.prev ?: RemoteMediator.MediatorResult.Success(remoteKeys != null)
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                remoteKeys?.next ?: RemoteMediator.MediatorResult.Success(remoteKeys != null)
            }
        }
    }

    private fun getFirstRemoteKey(state: PagingState<Key, Value>): RemoteKey? {
        state.firstItemOrNull()
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let {
                getKey(it)
            }
    }

    private fun getLastRemoteKey(state: PagingState<Key, Value>): RemoteKey? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let {
                getKey(it)
            }
    }

    companion object {
        private const val INITIAL_LOAD = 1
    }
}
