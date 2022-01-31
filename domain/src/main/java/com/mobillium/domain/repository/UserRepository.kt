package com.mobillium.domain.repository

import com.mobillium.domain.model.Common
import com.mobillium.domain.model.User

interface UserRepository {
    suspend fun login(username: String, password: String)
    suspend fun register(username: String, email: String, password: String)
    suspend fun forgotPassword(email: String)
    suspend fun logout(): Common
    suspend fun getUser(id: Int): User
    suspend fun followUser(id: Int): Common
    suspend fun unfollowUser(id: Int): Common
}
