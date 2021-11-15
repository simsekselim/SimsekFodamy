package com.mobillium.simsekfodamy.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import javax.sql.CommonDataSource


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesLoginRepository(logiDataSource: LoginDataSource)
    =LoginRepository(logiDataSource)
}