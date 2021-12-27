package com.mobillium.simsekfodamy.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.asLiveData
import com.mobillium.simsekfodamy.presentation.loginflow.intro.preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


class PreferencesManager @Inject constructor(val context: Context) {
    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore("fodamyApp")
        val userToken = stringPreferencesKey("token")
        val user = intPreferencesKey("user_id")
    }

    suspend fun isLogin(): Boolean = getToken().isNotBlank()
    val token: Flow<String> = context.dataStore.data.map { preference ->
        preference[userToken] ?: ""

    }


    suspend fun isFirst(): String {
        //first operator to get a single value and stop collection from the flow.
        return context.dataStore.data.map { it[userToken] ?: "" }.first()
    }


    suspend fun saveToken(token: String) {
        context.dataStore.edit {
            it[userToken] = token

        }

    }

    suspend fun user(userId : Int) {
        context.dataStore.edit {
            it[user] = userId
        }

    }

    suspend fun removeToken() {
        context.dataStore.edit {
            it.remove(userToken)
        }


    }
    suspend fun getUser(): Int {
        //first operator to get a single value and stop collection from the flow.
        return context.dataStore.data.map { it[user] ?: 0 }.first()
    }

    suspend fun getToken(): String {
        //first operator to get a single value and stop collection from the flow.
        return context.dataStore.data.map { it[userToken] ?: "" }.first()
    }


}

