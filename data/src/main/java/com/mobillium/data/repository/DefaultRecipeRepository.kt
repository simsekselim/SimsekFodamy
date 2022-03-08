package com.mobillium.data.repository

import androidx.paging.*
import com.mobillium.data.local.dao.RecipeDao
import com.mobillium.data.local.dao.RemoteKeysDao
import com.mobillium.data.mapper.toDomainModel
import com.mobillium.data.mapper.toLocalModel
import com.mobillium.data.remote.api.RecipeService
import com.mobillium.data.utils.CommentsRemoteMediator
import com.mobillium.data.utils.RecipeEditorRemoteMediator
import com.mobillium.data.utils.RecipeLastAddedRemoteMediator
import com.mobillium.data.utils.RemoteMediatorCategories
import com.mobillium.domain.model.Category
import com.mobillium.domain.model.Comment
import com.mobillium.domain.model.Common
import com.mobillium.domain.model.Recipe
import com.mobillium.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
class DefaultRecipeRepository
@Inject constructor(
    private val recipeService: RecipeService,
    private val recipeDao: RecipeDao,
    private val remoteKeysDao: RemoteKeysDao
) : RecipeRepository, BaseRepository() {

    private val pageConfig = PagingConfig(
        pageSize = PAGE_SIZE,
        maxSize = MAX_SIZE,
        enablePlaceholders = false
    )

    override suspend fun getEditorChoicePaging(): Flow<PagingData<Recipe>> =
        execute {
            val pagingSourceFactory = { recipeDao.getEditorChoicesPaging() }
            Pager(
                config = pageConfig,
                remoteMediator = RecipeEditorRemoteMediator(
                    recipeService = recipeService,
                    recipeDao = recipeDao,
                    remoteKeysDao = remoteKeysDao
                ),
                pagingSourceFactory = pagingSourceFactory
            ).flow.map { pagingData ->
                pagingData.map {
                    it.toDomainModel()
                }
            }
        }

    override suspend fun getLastAddedPaging(): Flow<PagingData<Recipe>> =
        execute {
            val pagingSourceFactory = { recipeDao.getLastAddedPaging() }
            Pager(
                config = pageConfig,
                remoteMediator = RecipeLastAddedRemoteMediator(
                    recipeService = recipeService,
                    recipeDao = recipeDao,
                    remoteKeysDao = remoteKeysDao
                ),
                pagingSourceFactory = pagingSourceFactory
            ).flow.map { pagingData ->
                pagingData.map {
                    it.toDomainModel()
                }
            }
        }

    override suspend fun getRecipeCommentsPaging(recipeId: Int): Flow<PagingData<Comment>> =
        execute {
            val pagingSourceFactory = { recipeDao.getRecipeCommentsPaging(recipeId) }
            Pager(
                config = pageConfig,
                remoteMediator = CommentsRemoteMediator(
                    recipeService = recipeService,
                    recipeDao = recipeDao,
                    remoteKeysDao = remoteKeysDao,
                    recipeId = recipeId
                ),
                pagingSourceFactory = pagingSourceFactory
            ).flow.map { pagingData ->
                pagingData.map { it.toDomainModel() }
            }
        }

    override suspend fun getRecipe(recipeId: Int): Recipe =
        execute {
            recipeService.getRecipe(recipeId).toDomainModel()
        }

    override suspend fun getLastAddedRecipes(page: Int): List<Recipe> =
        execute {
            val local = fetchFromLocal { recipeDao.getLastAdded().map { it.toDomainModel() } }
            if (local?.isNotEmpty() == true) {
                local
            } else {
                val remote = recipeService.getLastAddedRecipes(page).data
                saveToLocal { recipeDao.insertRecipes(remote.map { it.toLocalModel(is_last_added = true) }) }
                remote.map { it.toDomainModel() }
            }
        }

    override suspend fun getEditorChoiceRecipes(page: Int): List<Recipe> =
        execute {
            val local = fetchFromLocal { recipeDao.getEditorChoices().map { it.toDomainModel() } }
            if (local?.isNotEmpty() == true) {
                local
            } else {
                val remote = recipeService.getEditorChoiceRecipes(page).data
                saveToLocal { recipeDao.insertRecipes(remote.map { it.toLocalModel() }) }
                remote.map { it.toDomainModel() }
            }
        }

    override suspend fun getRecipeCategories(page: Int): List<Category> =
        execute {
            recipeService.getRecipeCategories(page).data.map {
                it.toDomainModel()
            }
                .filter {
                    it.recipes!!.isNotEmpty()
                }
        }

    override suspend fun getCategoriesPaging(): Flow<PagingData<Category>> =
        execute {
            val pagingSourceFactory = { recipeDao.getCategoriesPaging() }
            Pager(
                config = pageConfig,
                remoteMediator = RemoteMediatorCategories(
                    recipeService = recipeService,
                    recipeDao = recipeDao,
                    remoteKeysDao = remoteKeysDao
                ),
                pagingSourceFactory = pagingSourceFactory
            ).flow.map { pagingData ->
                pagingData.map { it.toDomainModel() }
            }
        }

    override suspend fun getCategoryRecipes(categoryId: Int, page: Int): List<Recipe> =
        execute {
            recipeService.getCategoryRecipes(categoryId, page).data.map {
                it.toDomainModel()
            }
        }

    override suspend fun getRecipeComments(recipeId: Int, page: Int): List<Comment> =
        execute {
            recipeService.getRecipeComments(recipeId, page).data.map {
                it.toDomainModel()
            }
        }

    override suspend fun getFirstComment(recipeId: Int): Comment =
        execute {
            recipeService.getRecipeComments(recipeId, 1).data[0].toDomainModel()
        }

    override suspend fun sendComment(recipeId: Int, text: String): Unit =
        execute {
            recipeService.sendComment(recipeId, text).toDomainModel()
        }

    override suspend fun likeRecipe(recipeId: Int): Common =
        execute {
            recipeService.likeRecipe(recipeId).toDomainModel()
        }

    override suspend fun dislikeRecipe(recipeId: Int): Common =
        execute {
            recipeService.dislikeRecipe(recipeId).toDomainModel()
        }

    override suspend fun editRecipeComments(recipeId: Int, comment: Int, text: String): Unit =
        execute {
            recipeService.editRecipeComments(recipeId, comment, text).toDomainModel()
        }

    override suspend fun deleteRecipeComments(recipeId: Int, comment: Int): Unit =
        execute {
            recipeService.deleteRecipeComments(recipeId, comment).toDomainModel()
        }

    companion object {
        private const val PAGE_SIZE = 24
        private const val MAX_SIZE = 100
    }
}
