package com.example.news_app.data.manger

// Required imports for working with Android DataStore and coroutines.
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.news_app.domain.manger.LocalUserManger
import com.example.news_app.util.Constants
import com.example.news_app.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// This class implements LocalUserManger interface and is responsible for handling local user data using DataStore.
class LocalUserMangerImpl(
    private val context: Context // Dependency injection of the application context.
) : LocalUserManger {

    // Method to save a Boolean value in DataStore to indicate the app's entry state (first-time entry).
    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings -> // Access DataStore and make an edit.
            settings[PreferenceKeys.APP_ENTRY] = true // Set the "APP_ENTRY" key to true.
        }
    }

    // Method to read the app entry state from DataStore. It returns a Flow of Boolean values.
    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data // Access DataStore's data.
            .map { preferences -> // Map the preferences to retrieve the value of the "APP_ENTRY" key.
                preferences[PreferenceKeys.APP_ENTRY] ?: false // If the key is not found, return false by default.
            }
    }
}

// Declares a property delegate for the DataStore using a read-only property.
private val readOnlyProperty = preferencesDataStore(name = USER_SETTINGS)

// Extension property for the Context class to easily access DataStore instance.
val Context.dataStore: DataStore<Preferences> by readOnlyProperty

// Object that holds the preference keys used in DataStore. It includes a Boolean key for the app's entry status.
private object PreferenceKeys {
    val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY) // Define a boolean preference key for "APP_ENTRY".
}
