    package com.mobillium.simsekfodamy.repository

import com.mobillium.simsekfodamy.api.UserService
import com.mobillium.simsekfodamy.model.User
import com.mobillium.simsekfodamy.response.BaseResponse
import com.mobillium.simsekfodamy.utils.PreferencesManager
import javax.inject.Inject
import javax.inject.Singleton
import com.mobillium.simsekfodamy.utils.Result

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.HttpException


    interface UserRepository {
    suspend fun login(username: String, password: String): Result<String>
    suspend fun register(username: String, email: String, password: String): Result<String>
    suspend fun forgotPassword(email: String): Result<Any>
    suspend fun logout(): Result<BaseResponse<Any>>
    suspend fun getUser(id: Int): Result<User>
    suspend fun followUser(id: Int): Result<BaseResponse<Any>>
    suspend fun unfollowUser(id: Int): Result<BaseResponse<Any>>


}

@Singleton
class DefaultUserRepository @Inject constructor(
    private val service: UserService,
    private val preferences : PreferencesManager
) : UserRepository {
    override suspend fun login(username: String, password: String): Result<String> {
        return try {
            val result =
                service.login(
                    username = username,
                    password = password

                )
            preferences.saveToken(result.token)
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
    override suspend fun forgotPassword(email: String): Result<Any> {
        return try {
            Result.Success(
                service.forgotPassword(
                    email = email
                )
            )
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

    override suspend fun logout(): Result<BaseResponse<Any>> {
        return try {
            val result = service.logout()
            preferences.removeToken()
            Result.Success(result)
        }catch (exception :Exception){
            Result.Error(exception)
        }

    }

    override suspend fun getUser(id: Int): Result<User> {
        return try {
            val user = service.getUser(id)
            Result.Success(user)
        } catch (e: HttpException) {
            Result.Error(e)
        }
    }

    override suspend fun followUser(id: Int): Result<BaseResponse<Any>> {
        return try {
            Result.Success(service.followUser(id))
        } catch (e: HttpException) {
            Result.Error(e)
        }
    }

    override suspend fun unfollowUser(id: Int): Result<BaseResponse<Any>> {
        return try {
            Result.Success(service.unfollowUser(id))
        } catch (e: HttpException) {
            Result.Error(e)
        }
    }


}

@Module
@InstallIn(SingletonComponent::class)
interface LoginModules {
    @Binds
    fun provideUserRepository(repository: DefaultUserRepository): UserRepository
}