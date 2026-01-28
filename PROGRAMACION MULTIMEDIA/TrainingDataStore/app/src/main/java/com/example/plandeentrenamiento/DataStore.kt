package com.example.plandeentrenamiento

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "MyPreferences"
)

class DataStoreManager(private val context: Context) {
    private val userKey = stringPreferencesKey("User")
    private val passKey = stringPreferencesKey("Pass")
    private val daysKey = intPreferencesKey("DAYS")
    private val weeksKey = intPreferencesKey("WEEKS")

    suspend fun saveUser(user: String, pass: String) {
        context.dataStore.edit { prefs ->
            prefs[userKey] = user
            prefs[passKey] = pass
        }
    }

    fun getUser(): Flow<String?> {
        return context.dataStore.data.map { prefs ->
            prefs[userKey]
        }
    }

    fun getPass(): Flow<String?> {
        return context.dataStore.data.map { prefs ->
            prefs[passKey]
        }
    }

    suspend fun saveDaysWeeks(days: Int, weeks: Int) {
        context.dataStore.edit { prefs ->
            prefs[daysKey] = days
            prefs[weeksKey] = weeks
        }
    }

    fun getDays(): Flow<Int?> {
        return context.dataStore.data.map { prefs ->
            prefs[daysKey]
        }
    }

    fun getWeeks(): Flow<Int?> {
        return context.dataStore.data.map { prefs ->
            prefs[weeksKey]
        }
    }
}