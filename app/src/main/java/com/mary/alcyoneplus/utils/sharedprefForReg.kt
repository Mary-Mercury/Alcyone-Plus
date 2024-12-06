package com.mary.alcyoneplus.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class sharedprefForReg @Inject constructor(
    @ApplicationContext private val context: Context) {

    companion object {
        private const val MY_PREF_KEY = "MY_PREF"
    }

    fun saveStringData(key: String, data: String) {
        val sharedPreferences = context.getSharedPreferences(MY_PREF_KEY, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(key, data).apply()
    }

    fun getStringData(key: String): String? {
        val sharedPreferences = context.getSharedPreferences(MY_PREF_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }

    fun clearPreferences() {
        val sharedPreferences = context.getSharedPreferences(MY_PREF_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.edit().clear().apply()

    }
}