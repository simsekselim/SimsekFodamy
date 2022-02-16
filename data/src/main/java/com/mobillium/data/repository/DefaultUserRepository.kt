package com.mobillium.data.repository

import com.mobillium.data.api.UserService
import com.mobillium.data.mapper.toDomainModel
import com.mobillium.data.utils.PreferencesManager
import com.mobillium.domain.model.Common
import com.mobillium.domain.model.User
import com.mobillium.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultUserRepository @Inject constructor(
    private val userService: UserService,
    private val preferences: PreferencesManager
) : UserRepository, BaseRepository() {
    override suspend fun login(username: String, password: String): Unit =
        execute {
            val response = userService.login(username, password).toDomainModel()
            preferences.saveToken(response.token)
            preferences.user(response.user.id)
        }

    override suspend fun register(username: String, email: String, password: String): Unit =
        execute {
            userService.register(username, email, password).toDomainModel()
        }

    override suspend fun forgotPassword(email: String): Unit =
        execute {
            userService.forgotPassword(email).toDomainModel()
        }

    override suspend fun logout(): Common =

        execute {
            val response = userService.logout().toDomainModel()
            preferences.removeToken()
            response
        }

    override suspend fun getUser(id: Int): User =
        execute {
            userService.getUser(id).toDomainModel()
        }

    override suspend fun followUser(id: Int): Common =
        execute {
            userService.followUser(id).toDomainModel()
        }

    override suspend fun unfollowUser(id: Int): Common =
        execute {
            userService.unfollowUser(id).toDomainModel()
        }
}
