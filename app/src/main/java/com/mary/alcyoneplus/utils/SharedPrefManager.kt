package com.mary.alcyoneplus.utils

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SharedPrefManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun saveFirstLaunch(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getFirstLaunch(key: String, value: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, value)
    }
}
