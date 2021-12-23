package com.mobillium.simsekfodamy.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


class PreferencesManager @Inject constructor(val context: Context) {
    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore("fodamyApp")
            val userToken = stringPreferencesKey("token")
    }

    suspend fun isLogin(): Boolean = getToken().isNotBlank()
    val token: Flow<String> = context.dataStore.data.map { preference ->
        preference[userToken] ?: ""

    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit {
            it[userToken] = token

        }

    }

    suspend fun removeToken() {
        context.dataStore.edit {
            it.remove(userToken)
        }


    }

    suspend fun getToken(): String {
        //first operator to get a single value and stop collection from the flow.
        return context.dataStore.data.map { it[userToken] ?: "" }.first()
    }
}
