package com.mobillium.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobillium.data.local.model.RemoteKeyCategory
import com.mobillium.data.local.model.RemoteKeyComment
import com.mobillium.data.local.model.RemoteKeyEditor
import com.mobillium.data.local.model.RemoteKeyLast

@Dao
interface RemoteKeysDao {

    // EditorChoices Recipes Remote Keys
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEditorRemoteKeys(remoteKeys: List<RemoteKeyEditor>)

    @Query("select * from remote_keys_editor where id =:id")
    fun getEditorRemoteKeys(id: Int): RemoteKeyEditor

    @Query("delete from remote_keys_editor")
    fun deleteEditorKeys()

    // Last Added Recipes Remote Keys
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLasAtAddedRemoteKeys(remoteKeys: List<RemoteKeyLast>)

    @Query("select * from remote_keys_last where id =:id")
    fun getLastAddedRemoteKeys(id: Int): RemoteKeyLast

    @Query("delete from remote_keys_last")
    fun deleteLastKeys()

    // Recipe Comments Remote Key
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCommentsRemoteKeys(remoteKeys: List<RemoteKeyComment>)

    @Query("select * from remote_key_comments where id =:id")
    fun getCommentsRemoteKey(id: Int): RemoteKeyComment

    @Query("delete from remote_key_comments")
    fun deleteCommentsRemoteKeys()

    // Category Recipes Remote Key
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategoryRecipesRemoteKeys(remoteKeys: List<RemoteKeyCategory>)

    @Query("select * from remote_keys_category where id =:id")
    fun getCategoryRemoteKeys(id: Int): RemoteKeyCategory

    @Query("delete from remote_keys_category")
    fun deleteCategoryRecipesKeys()
}
