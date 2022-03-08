package com.mobillium.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryLocal(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    @Embedded
    val image: ImageLocal,
    val recipes: List<RecipeLocal>
)
