package com.mobillium.simsekfodamy.di

import android.content.Context
import com.mobillium.simsekfodamy.BuildConfig
import com.mobillium.simsekfodamy.api.RecipeService
import com.mobillium.simsekfodamy.api.UserService
import com.mobillium.simsekfodamy.repository.DefaultRecipeRepository
import com.mobillium.simsekfodamy.repository.DefaultUserRepository
import com.mobillium.simsekfodamy.repository.RecipeRepository
import com.mobillium.simsekfodamy.repository.UserRepository
import com.mobillium.simsekfodamy.utils.Constants.BASE_URL
import com.mobillium.simsekfodamy.utils.PreferencesManager
import com.mobillium.simsekfodamy.utils.UserInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideAuthInterceptorOkHttpClient(
        interceptor: UserInterceptor
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providePreferencesManager(@ApplicationContext context: Context): PreferencesManager =
        PreferencesManager(context)

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): RecipeService =
        retrofit.create(RecipeService::class.java)

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
