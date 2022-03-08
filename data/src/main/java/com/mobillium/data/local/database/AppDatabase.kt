package com.mobillium.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mobillium.data.local.converters.CategoryConverter
import com.mobillium.data.local.converters.ImageConverter
import com.mobillium.data.local.converters.RecipeConverter
import com.mobillium.data.local.converters.UserConverter
import com.mobillium.data.local.dao.RecipeDao
import com.mobillium.data.local.dao.RemoteKeysDao
import com.mobillium.data.local.dao.UserDao
import com.mobillium.data.local.model.*

@Database(
    entities = [
        RecipeLocal::class,
        CategoryLocal::class,
        CommentLocal::class,
        UserLocal::class,
        RemoteKeyEditor::class,
        RemoteKeyLast::class,
        RemoteKeyComment::class,
        RemoteKeyCategory::class
    ],
    version = 4,
    exportSchema = false
)
@TypeConverters(
    CategoryConverter::class,
    ImageConverter::class,
    UserConverter::class,
    RecipeConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract val recipeDao: RecipeDao
    abstract val userDao: UserDao
    abstract val remoteKeysDao: RemoteKeysDao
}
