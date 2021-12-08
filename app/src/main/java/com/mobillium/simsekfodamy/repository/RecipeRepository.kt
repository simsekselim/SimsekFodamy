package com.mobillium.simsekfodamy.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.mobillium.simsekfodamy.api.RecipeService
import com.mobillium.simsekfodamy.model.Category
import com.mobillium.simsekfodamy.utils.Result
import com.mobillium.simsekfodamy.model.Recipe
import com.mobillium.simsekfodamy.utils.CategoryPagingFactory
import com.mobillium.simsekfodamy.utils.RecipePagingFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

interface RecipeRepository {
    suspend fun getRecipe(id: Int): Result<Recipe>
    fun getLastAddedRecipes(): Flow<PagingData<Recipe>>
    fun getEditorChoiceRecipes(): Flow<PagingData<Recipe>>
    fun getRecipeCategories(): Flow<PagingData<Category>>
    fun getCategoryRecipes(categoryId: Int): Flow<PagingData<Recipe>>

}

@Singleton
class DefaultRecipeRepository @Inject constructor(
    private val recipeService: RecipeService
) : RecipeRepository {
    override suspend fun getRecipe(id: Int): Result<Recipe> {
        return try {
            Result.Success(recipeService.getRecipe(id))
        } catch (e: HttpException) {
            Result.Error(e)
        }
    }

    override fun getLastAddedRecipes() = Pager(
        config = PagingConfig(
            pageSize = 24,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            RecipePagingFactory(
                recipeService,
                RecipePagingFactory.GET_LAST,
                0

                )
        }
    ).flow

    override fun getEditorChoiceRecipes() = Pager(
        config = PagingConfig(
            pageSize = 24,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            RecipePagingFactory(
                recipeService,
                RecipePagingFactory.GET_EDITOR_CHOICE,
                0

                )
        }
    ).flow

    override fun getRecipeCategories() = Pager(
        config = PagingConfig(
            pageSize = 4,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            CategoryPagingFactory(
                recipeService
            )
        }
    ).flow

    override fun getCategoryRecipes(categoryId: Int) = Pager(
        config = PagingConfig(
            pageSize = 24,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            RecipePagingFactory(
                recipeService,
                RecipePagingFactory.GET_CATEGORY_RECIPES,
                categoryId
            )
        }
    ).flow

}

@Module
@InstallIn(SingletonComponent::class)
interface RecipeModules {
    @Binds
    fun provideRecipeRepository(repository: DefaultRecipeRepository): RecipeRepository
}


