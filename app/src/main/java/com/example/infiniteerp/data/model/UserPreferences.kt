package com.example.infiniteerp.data.model

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    fun getUser(): Flow<UserModel> {
        return dataStore.data.map {
            UserModel(
                it[USERNAME_KEY] ?: "",
                it[PASSWORD_KEY] ?: "",
                it[CLIENTID_KEY] ?: "",
                it[ORGID_ID] ?: "",
                it[TOKEN_KEY] ?: "",
                it[STATE_KEY] ?: false,

                )

        }
    }

    suspend fun saveUser(user: UserModel) {
        dataStore.edit {
            it[USERNAME_KEY] = user.username
            it[PASSWORD_KEY] = user.password
            it[CLIENTID_KEY] = user.clientId
            it[ORGID_ID] = user.orgId
            it[TOKEN_KEY] = user.token
            it[STATE_KEY] = user.isLogin
        }
    }

    suspend fun logout() {
        dataStore.edit {
            it[USERNAME_KEY] ?: ""
            it[PASSWORD_KEY] ?: ""
            it[CLIENTID_KEY] ?: ""
            it[ORGID_ID] ?: ""
            it[TOKEN_KEY] ?: ""
            it[STATE_KEY] ?: false

        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null


        private val USERNAME_KEY = stringPreferencesKey("username")
        private val PASSWORD_KEY = stringPreferencesKey("password")
        private val ORGID_ID = stringPreferencesKey("orgId")
        private val CLIENTID_KEY = stringPreferencesKey("clientId")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val STATE_KEY = booleanPreferencesKey("state")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}