package com.mobillium.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobillium.data.local.model.CategoryLocal
import com.mobillium.data.local.model.CommentLocal
import com.mobillium.data.local.model.RecipeLocal

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: List<RecipeLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(category: List<CategoryLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(commentDbs: List<CommentLocal>)

    @Query("select * from recipes where id =:recipeId")
    suspend fun getRecipeDetails(recipeId: Int): RecipeLocal

    @Query("select * from recipes where is_editor_choice = 1")
    fun getEditorChoicesPaging(): PagingSource<Int, RecipeLocal>

    @Query("select * from recipes where is_last_added = 1 order by id desc")
    fun getLastAddedPaging(): PagingSource<Int, RecipeLocal>

    @Query("select * from recipes where is_editor_choice =1 order by id desc")
    suspend fun getEditorChoices(): List<RecipeLocal>

    @Query("select * from recipes where is_last_added=1 order by id desc")
    suspend fun getLastAdded(): List<RecipeLocal>

    @Query("select * from categories")
    suspend fun getCategories(): List<CategoryLocal>

    @Query("select * from categories")
    fun getCategoriesPaging(): PagingSource<Int, CategoryLocal>

    @Query("select * from categories where id =:categoryId")
    suspend fun getRecipesByCategory(categoryId: Int): CategoryLocal

    @Query("select * from comments where recipe_id =:recipeId")
    fun getRecipeCommentsPaging(recipeId: Int): PagingSource<Int, CommentLocal>

    @Query("select * from comments where recipe_id =:recipeId")
    suspend fun getRecipeComments(recipeId: Int): List<CommentLocal>

    @Query("select * from comments where recipe_id =:recipeId limit 1")
    suspend fun getFirstRecipeComments(recipeId: Int): CommentLocal
}
