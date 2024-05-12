package com.sebas.booksapp

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

class AuthStore(private val context: Context) {
	companion object {
		suspend fun getToken(context: Context): String {
			return context.dataStore.data.first()[stringPreferencesKey("token")] ?: ""
		}

		suspend fun saveToken(token: String, context: Context) {
			context.dataStore.edit { preferences ->
				preferences[stringPreferencesKey("token")] = token
			}
		}
	}
}