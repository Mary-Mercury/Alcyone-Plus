package com.mary.alcyoneplus.Data

sealed class userState {
    object Loading: userState()
    data class Success(val message: String): userState()
    data class Error(val message: String): userState()
}