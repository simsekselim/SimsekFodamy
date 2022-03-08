package com.mobillium.data.di

import android.content.Context
import androidx.room.Room
import com.mobillium.data.local.converters.ImageConverter
import com.mobillium.data.local.converters.RecipeConverter
import com.mobillium.data.local.dao.RecipeDao
import com.mobillium.data.local.dao.RemoteKeysDao
import com.mobillium.data.local.dao.UserDao
import com.mobillium.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRecipeDao(database: AppDatabase): RecipeDao {
        return database.recipeDao
    }

    @Singleton
    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao
    }

    @Provides
    @Singleton
    fun provideRecipeConverter(): RecipeConverter {
        return RecipeConverter()
    }

    @Provides
    @Singleton
    fun provideRemoteKeysDao(appDatabase: AppDatabase): RemoteKeysDao {
        return appDatabase.remoteKeysDao
    }

    @Provides
    @Singleton
    fun provideImageConverter(): ImageConverter {
        return ImageConverter()
    }

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
        recipeConverter: RecipeConverter,
        imageConverter: ImageConverter
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "recipe.db"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .addTypeConverter(recipeConverter)
            .addTypeConverter(imageConverter)
            .build()
    }
}
