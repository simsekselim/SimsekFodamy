package com.mobillium.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeLocal(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "category")
    val category: CategoryLocal,
    val definition: String,
    val difference: String,
    val directions: String,
    val images: List<ImageLocal>,
    val ingredients: String,
    @ColumnInfo(name = "is_editor_choice")
    val is_editor_choice: Boolean,
    @ColumnInfo(name = "is_last_added")
    val is_last_added: Boolean = false,
    @ColumnInfo(name = "is_liked")
    val is_liked: Boolean,
    @ColumnInfo(name = "like_count")
    val like_count: Int,
    @ColumnInfo(name = "comment_count")
    val comment_count: Int,
    @Embedded(prefix = "time_of_recipe")
    val time_of_recipe: TimeOfRecipeLocal,
    @Embedded(prefix = "number_of_person")
    val number_of_person: NumberOfPersonLocal,
    val title: String,
    @ColumnInfo(name = "user")
    val user: UserLocal,

)
