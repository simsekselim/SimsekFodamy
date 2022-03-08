package com.mobillium.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mobillium.data.utils.RemoteKey

@Entity(tableName = "remote_keys_editor")
data class RemoteKeyEditor(
    @PrimaryKey(autoGenerate = false)
    override val id: Int,
    override val prev: Int?,
    override val next: Int?
) : RemoteKey
