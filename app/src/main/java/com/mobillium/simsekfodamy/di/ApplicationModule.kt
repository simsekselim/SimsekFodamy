package com.mobillium.simsekfodamy.di

import android.content.Context
import com.mobillium.data.repository.DefaultRecipeRepository
import com.mobillium.data.repository.DefaultUserRepository
import com.mobillium.data.utils.PreferencesManager
import com.mobillium.domain.repository.RecipeRepository
import com.mobillium.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun providePreferencesManager(@ApplicationContext context: Context): PreferencesManager =
        PreferencesManager(context)

    @Module
    @InstallIn(SingletonComponent::class)
    interface LoginModules {
        @Binds
        fun provideUserRepository(repository: DefaultUserRepository): UserRepository
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface RecipeModules {
        @Binds
        fun provideRecipeRepository(repository: DefaultRecipeRepository): RecipeRepository
    }
}
