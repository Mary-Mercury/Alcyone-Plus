package com.mary.alcyoneplus.utils

import kotlinx.coroutines.flow.Flow

interface ConnectivityManager {

    fun observe(): Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}