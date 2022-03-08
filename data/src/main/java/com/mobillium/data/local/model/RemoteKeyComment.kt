package com.mobillium.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mobillium.data.utils.RemoteKey

@Entity(tableName = "remote_key_comments")
data class RemoteKeyComment(
    @PrimaryKey(autoGenerate = false)
    override val id: Int,
    override val next: Int?,
    override val prev: Int?
) : RemoteKey
