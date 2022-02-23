package com.mobillium.local

import androidx.room.RoomDatabase

abstract class RecipeDatabase : RoomDatabase() {
    abstract val recipeDao : RecipeDao
}