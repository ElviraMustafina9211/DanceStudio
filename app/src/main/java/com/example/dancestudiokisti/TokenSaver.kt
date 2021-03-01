package com.example.dancestudiokisti

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

val KEY_TOKEN = stringPreferencesKey("token")

class TokenSaver(private val context: Context) {
    var token: String?
        get() {
            return runBlocking { context.dataStore.data.map { it[KEY_TOKEN] }.first() }
        }
        set(value) {
            runBlocking {
                context.dataStore.edit {
                    if (value != null) {
                        it[KEY_TOKEN] = value
                    } else {
                        it.remove(KEY_TOKEN)
                    }
                }
            }
        }
}