package com.chichi289.week3.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.chichi289.week3.domain.LocalRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(
    name = "user_preferences"
)

@Singleton
class LocalRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : LocalRepository {

    private object PreferencesKeys {
        val KEY_USER_ADDED_TO_DB = booleanPreferencesKey("is_user_added_to_db")
    }

    override var isUserStoredInDb: Flow<Boolean> = context.dataStore.data.catch { exception ->
        // dataStore.data throws an IOException when an error is encountered when reading data
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }

    }.map { preferences ->
        preferences[PreferencesKeys.KEY_USER_ADDED_TO_DB] ?: false
    }

    override suspend fun setUserAddedToDb(b: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.KEY_USER_ADDED_TO_DB] = b
        }
    }

}