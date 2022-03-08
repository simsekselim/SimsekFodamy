package com.mobillium.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class CommentLocal(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val text: String,
    val difference: String,
    val user: UserLocal,
    @ColumnInfo(name = "recipe_id")
    val recipeId: Int
)
