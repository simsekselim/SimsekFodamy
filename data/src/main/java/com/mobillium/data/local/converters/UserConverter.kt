package com.mobillium.data.local.converters

import androidx.room.TypeConverter
import com.mobillium.data.local.model.UserLocal

class UserConverter {

    @TypeConverter
    fun userToJson(user: UserLocal): String {
        return toJson<UserLocal>(user)
    }

    @TypeConverter
    fun jsonToUser(user: String): UserLocal {
        return fromJson(user)
    }
}
