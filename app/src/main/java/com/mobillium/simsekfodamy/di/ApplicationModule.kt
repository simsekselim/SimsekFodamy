package com.mobillium.simsekfodamy.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import com.mobillium.data.local.dao.RecipeDao
import com.mobillium.data.local.dao.RemoteKeysDao
import com.mobillium.data.local.dao.UserDao
import com.mobillium.data.remote.api.RecipeService
import com.mobillium.data.remote.api.UserService
import com.mobillium.data.repository.DefaultRecipeRepository
import com.mobillium.data.repository.DefaultUserRepository
import com.mobillium.data.utils.PreferencesManager
import com.mobillium.domain.repository.RecipeRepository
import com.mobillium.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun providePreferencesManager(@ApplicationContext context: Context): PreferencesManager =
        PreferencesManager(context)

    @Provides
    @Singleton
    fun provideUserRepository(
        userService: UserService,
        userDao: UserDao,
        preferencesManager: PreferencesManager
    ): UserRepository {
        return DefaultUserRepository(userService, userDao, preferencesManager)
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeDao: RecipeDao,
        remoteKeysDao: RemoteKeysDao
    ): RecipeRepository {
        return DefaultRecipeRepository(recipeService, recipeDao, remoteKeysDao)
    }
}
