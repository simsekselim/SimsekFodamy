package com.mobillium.data.repository

import com.mobillium.data.api.RecipeService
import com.mobillium.data.mapper.toDomainModel
import com.mobillium.domain.model.*
import com.mobillium.domain.repository.RecipeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRecipeRepository @Inject constructor(
    private val recipeService: RecipeService
) : RecipeRepository, BaseRepository() {
    override suspend fun getRecipe(id: Int): Recipe =
        execute {
            recipeService.getRecipe(id).toDomainModel()
        }

    override suspend fun getLastAddedRecipes(page: Int): List<Recipe> =
        execute {
            recipeService.getLastAddedRecipes(page).data.map {
                it.toDomainModel()
            }
        }

    override suspend fun getEditorChoiceRecipes(page: Int): List<Recipe> =
        execute {
            recipeService.getEditorChoiceRecipes(page).data.map {
                it.toDomainModel()
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

    override suspend fun getCategoryRecipes(categoryId: Int, page: Int): List<Recipe> =
        execute {
            recipeService.getCategoryRecipes(categoryId, page).data.map {
                it.toDomainModel()
            }
        }

    override suspend fun getRecipeComments(recipeId: Int, page: Int): List<Comment> =
        execute{
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
}
