package com.mobillium.data.local.datasource

import com.mobillium.data.di.IODispatcher
import com.mobillium.data.local.dao.RecipeDao
import com.mobillium.data.local.model.CategoryLocal
import com.mobillium.data.local.model.CommentLocal
import com.mobillium.data.local.model.RecipeLocal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalRecipeDataSource @Inject constructor(
    private val recipeDao: RecipeDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun saveRecipe(recipe: List<RecipeLocal>) {
        return withContext(ioDispatcher) {
            recipeDao.insertRecipes(recipe)
        }
    }

    suspend fun saveCategories(category: List<CategoryLocal>) {
        return withContext(ioDispatcher) {
            recipeDao.insertCategories(category)
        }
    }

    suspend fun saveComments(commentDb: List<CommentLocal>) {
        return withContext(ioDispatcher) {
            recipeDao.insertComments(commentDb)
        }
    }

    suspend fun getRecipeDetails(recipeId: Int): RecipeLocal {
        return recipeDao.getRecipeDetails(recipeId)
    }

    suspend fun getEditorChoice(): List<RecipeLocal> {
        return recipeDao.getEditorChoices()
    }

    suspend fun getLastAdded(): List<RecipeLocal> {
        return recipeDao.getLastAdded()
    }

    suspend fun getCategories(): List<CategoryLocal> {
        return recipeDao.getCategories()
    }

    suspend fun getRecipeByCategory(categoryId: Int): CategoryLocal {
        return recipeDao.getRecipesByCategory(categoryId)
    }

    suspend fun getRecipeComments(recipeId: Int): List<CommentLocal> {
        return recipeDao.getRecipeComments(recipeId)
    }

    suspend fun getFirstRecipeComments(recipeId: Int): CommentLocal {
        return recipeDao.getFirstRecipeComments(recipeId)
    }
}
