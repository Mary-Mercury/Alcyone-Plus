package com.mary.alcyoneplus.UI

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mary.alcyoneplus.Data.ApiResult
import com.mary.alcyoneplus.Data.NewsDto
import com.mary.alcyoneplus.Data.repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.mary.alcyoneplus.Data.TableTestDto
import com.mary.alcyoneplus.Data.userState
import com.mary.alcyoneplus.utils.DataStoreManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import androidx.compose.runtime.State
import com.mary.alcyoneplus.utils.sharedprefForReg
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.signInAnonymously


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: repository,
    private val settingsDataStore: DataStoreManager,
    private val supabaseClient: SupabaseClient,
    private val sharedPrefForReg: sharedprefForReg
): ViewModel() {


    val switchState: StateFlow<Boolean> = settingsDataStore.switchFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, false)
    fun saveSwitchState(isEnabled: Boolean) {
        viewModelScope.launch {
            settingsDataStore.saveSwitchState(isEnabled)
        }
    }


    val selectedItem: StateFlow<Int> = settingsDataStore.selectedItemFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)
    fun saveSelectedItem(itemIndex: Int) {
        viewModelScope.launch {
            settingsDataStore.saveSelectedItem(itemIndex)
        }
    }


    //для получения данных для новостей
    private val _news = MutableStateFlow<ApiResult<List<NewsDto>>>(ApiResult.Loading)
    val news: StateFlow<ApiResult<List<NewsDto>>> get() = _news

    //для получения расписания
    private val _exampleFlowTest = MutableStateFlow<ApiResult<List<TableTestDto>>>(ApiResult.Loading)

    //для обмена данных выбранного дня
    private val _selectedDay = mutableStateOf("")

    //для обмена данных выбранной недели
    private val _selectedWeek = mutableStateOf("")



    //фильтрованные данные
    private val _filteredDataFlow = MutableStateFlow<ApiResult<List<TableTestDto>>>(ApiResult.Loading)
    val filteredDataFlow: StateFlow<ApiResult<List<TableTestDto>>> get() = _filteredDataFlow

    fun filterData(selectDay: String, selectWeek: String) {
        var dayOfWeek = selectDay
//        Log.e("ApiResult", dayOfWeek)
        var numOfWeek = selectWeek
//        Log.e("ApiResult", numOfWeek)
        viewModelScope.launch {
            _exampleFlowTest.collect { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        val filteredData = apiResult.data.filter { table ->
                            table.day == dayOfWeek && table.parity == numOfWeek
                        }
                        _filteredDataFlow.value = ApiResult.Success(filteredData)
                        Log.e("ApiResult2", _filteredDataFlow.value.toString())
                    }

                    is ApiResult.Error -> {
                        // Оставляем ошибку без изменений и сохраняем Loading в отфильтрованных данных
                        _filteredDataFlow.value = ApiResult.Loading
                    }

                    ApiResult.Loading -> {
                        // Оставляем состояние загрузки без изменений
                        _filteredDataFlow.value = ApiResult.Loading
                    }
                }
            }
        }
    }

    //для обновления выбранного дня
    fun updateDay(newDay: String) {
        _selectedDay.value = newDay
    }

    //для обновления выбранной недели
    fun updateWeek(newDay: String) {
        _selectedWeek.value = newDay
    }

    //инициализация выборки данных
    init {
        fetchNews()
        observeSelectedItem()
    }

    private fun observeSelectedItem() {
        // Подписка на изменения выбранного элемента
        viewModelScope.launch {
            settingsDataStore.selectedItemFlow.collectLatest { selectedItem ->
                fetchTables(selectedItem)
            }
        }
    }

    private fun fetchTables(selectedItem: Int) {
        viewModelScope.launch {
            when( selectedItem) {
                0 -> {
                    repository.getExampleFlowTest().collectLatest { data ->
                        _exampleFlowTest.update { data }
                    }
                }
                1 -> {
                    repository.getSchedule2111().collectLatest { data ->
                        _exampleFlowTest.update { data }
                    }
                }
            }
        }
    }

    private fun fetchNews() {
        viewModelScope.launch {
            repository.getNews().collectLatest { data ->
                _news.update { data }
                Log.e("TAGss", "getNews: $_news")
            }
        }
    }

//================================================================================================\\

//
    private val _userState = mutableStateOf<userState>(userState.Loading)
    val userStateLD: State<userState> = _userState
//
//    //функция регистарции
    fun signUp(
        context: Context,
        userEmail: String,
        userPassword: String
    ) {
        viewModelScope.launch {
            try {
                supabaseClient.auth.signUpWith(Email) {
                    email = userEmail
                    password = userPassword
                }
                saveToken(context = context)
                _userState.value = userState.Success("Registered user successfully!")
            } catch (e: Exception) {
                _userState.value = userState.Error("Error: ${e.message}")
            }
        }
    }
//
    fun anonymousLogin(context: Context,) {
        viewModelScope.launch {
            try {
                supabaseClient.auth.currentUserOrNull()
                saveToken(context = context)
                _userState.value = userState.Success("Logged user successfully!")
                Log.d("login", "авторизация  аноним успешно")
            } catch (e: Exception) {
                _userState.value = userState.Error("Error: ${e.message}")
            }
        }
    }
//
//    //функция входа в систему
    fun login(
        context: Context,
        userEmail: String,
        userPassword: String
    ) {
        viewModelScope.launch {
            try {
                supabaseClient.auth.signInWith(Email) {
                    email = userEmail
                    password = userPassword
                }
                saveToken(context = context)
                _userState.value = userState.Success("Logged user successfully!")
                Log.d("login", "авторизация успешно")
            } catch (e: Exception) {
                _userState.value = userState.Error("Error: ${e.message}")
            }
        }
    }
//
//    //функция выхода из системы
    fun logout(context: Context) {
        viewModelScope.launch {
            _userState.value = userState.Loading
            try {
                supabaseClient.auth.signOut()
                sharedPrefForReg.clearPreferences()
                _userState.value = userState.Success("Logged out successfully!")
            } catch (e: Exception) {
                _userState.value = userState.Error("Error: ${e.message}")
            }
        }
    }
//
//    //проверка, зашел ли пользователь в систему ранее
    fun isUserLoggedIn(context: Context) {
        viewModelScope.launch{
            try {
                val token = getToken(context)
                if (token.isNullOrEmpty()) {
                    _userState.value = userState.Error("User is not Logged in!")
                } else {
                    supabaseClient.auth.retrieveUser(token)
                    supabaseClient.auth.refreshCurrentSession()
                    saveToken(context = context)
                    _userState.value = userState.Success("User is already logged in!")
                }
            } catch (e: Exception) {
                _userState.value = userState.Error("Error: ${e.message}")
            }
        }
    }
//
private fun saveToken(context: Context) {
    viewModelScope.launch {
        val accessToken = supabaseClient.auth.currentAccessTokenOrNull() ?: ""
        sharedPrefForReg.saveStringData("accessToken", accessToken)
    }
}

    fun getToken(context: Context): String? {
        return sharedPrefForReg.getStringData("accessToken")
    }

}