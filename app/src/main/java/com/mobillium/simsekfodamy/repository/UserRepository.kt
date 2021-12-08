package com.mobillium.simsekfodamy.repository

import com.mobillium.simsekfodamy.api.UserService
import javax.inject.Inject
import javax.inject.Singleton
import com.mobillium.simsekfodamy.utils.Result

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


interface UserRepository {
    suspend fun login(username: String, password: String): Result<String>
    suspend fun register(username: String, email: String, password: String): Result<String>


}

@Singleton
class DefaultUserRepository @Inject constructor(
    private val service: UserService
) : UserRepository {
    override suspend fun login(username: String, password: String): Result<String> {
        return try {
            val result =
                service.login(
                    username = username,
                    password = password

                )

            Result.Success("")
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }


    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): Result<String> {
        return try {
            val result =
                service.register(
                    username = username,
                    email = email,
                    password = password
                )

            Result.Success("")
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface LoginModules {
    @Binds
    fun provideUserRepository(repository: DefaultUserRepository): UserRepository
}