package com.mary.alcyoneplus.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toSet
import javax.inject.Inject
import javax.inject.Singleton


private val Context.dataStore by preferencesDataStore(name = "settings")


@Singleton
class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        val SWITCH_KEY = booleanPreferencesKey("switch_key")
        val SELECTED_ITEM_KEY = intPreferencesKey("selected_item_key")
        val SELECTED_TAB = intPreferencesKey("selected_tab")
    }

    val switchFlow: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[SWITCH_KEY] ?: false
        }

    suspend fun saveSwitchState(isEnabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[SWITCH_KEY] = isEnabled
        }
    }

    suspend fun getSwitchState(): Boolean {
        val preferences = context.dataStore.data.first()
        return preferences[SWITCH_KEY] ?: false
    }

    suspend fun saveSelectedItem(itemIndex: Int) {
        context.dataStore.edit { preferences ->
            preferences[SELECTED_ITEM_KEY] = itemIndex
        }
    }

    val selectedItemFlow: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[SELECTED_ITEM_KEY] ?: 0
        }


    val getSelectedTab: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[SELECTED_TAB] ?: 0
        }

    suspend fun saveSelectedTab(tab: Int) {
        context.dataStore.edit { preferences ->
            preferences[SELECTED_TAB] = tab
        }
    }
}