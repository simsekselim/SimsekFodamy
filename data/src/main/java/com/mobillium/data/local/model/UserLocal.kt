package com.mobillium.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserLocal(
    @PrimaryKey(autoGenerate = false)
    val followed_count: Int,
    val following_count: Int,
    val favorites_count: Int,
    val id: Int,
    @Embedded
    val image: ImageLocal,
    val is_following: Boolean,
    val likes_count: Int,
    val recipe_count: Int,
    val name: String,
    val username: String

)
