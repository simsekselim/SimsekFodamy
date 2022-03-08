package com.mobillium.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobillium.data.local.model.UserLocal

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userDb: UserLocal)

    @Query("select * from user")
    suspend fun getUser(): UserLocal

    @Query("delete from user where id=:userId")
    suspend fun deleteUser(userId: Int)
}
