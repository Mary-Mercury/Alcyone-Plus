package com.mary.alcyoneplus.UI

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mary.alcyoneplus.Data.ApiResult
import com.mary.alcyoneplus.Data.NewsDto
import com.mary.alcyoneplus.Data.ScheduleDtoEXP
import com.mary.alcyoneplus.Data.ScheduleOfflineRepository
import com.mary.alcyoneplus.Data.repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.mary.alcyoneplus.Data.TableTestDto
import com.mary.alcyoneplus.Data.scheduleOfflineDto
import com.mary.alcyoneplus.utils.ConnectivityManager
import com.mary.alcyoneplus.utils.ConnectivityObserver
import com.mary.alcyoneplus.utils.DataStoreManager
import com.mary.alcyoneplus.utils.SharedPrefManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.stateIn


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: repository,
    private val settingsDataStore: DataStoreManager,
    private val sharedPrefManager: SharedPrefManager,
    private val offlineSchedule: ScheduleOfflineRepository,
    private val connectivityObserver: ConnectivityObserver
): ViewModel() {

    val isFirstLaunch = sharedPrefManager.getFirstLaunch("saveFirstLaunch", false)

    fun saveFirstLaunch(key: String, value: Boolean) {
        return sharedPrefManager.saveFirstLaunch(key, value)
    }

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

    //для получения всех данных расписания
    private val _exampleFlowTest = MutableStateFlow<ApiResult<List<TableTestDto>>>(ApiResult.Loading)
    private val _exampleFlowTestEXP = MutableStateFlow<ApiResult<List<ScheduleDtoEXP>>>(ApiResult.Loading)


    private val _exampleFlowTestEXPOffline = MutableStateFlow<List<scheduleOfflineDto>>(emptyList())

    //для обмена данных выбранного дня
    private val _selectedDay = mutableStateOf("")

    //для обмена данных выбранной недели
    private val _selectedWeek = mutableStateOf("")
    val selectWeek: State<String> = _selectedWeek



    //фильтрованные данные
    private val _filteredDataFlow = MutableStateFlow<ApiResult<List<TableTestDto>>>(ApiResult.Loading)
    val filteredDataFlow: StateFlow<ApiResult<List<TableTestDto>>> get() = _filteredDataFlow

    private val _filteredDataFlowEXP = MutableStateFlow<ApiResult<List<ScheduleDtoEXP>>>(ApiResult.Loading)
    val filteredDataFlowEXP: StateFlow<ApiResult<List<ScheduleDtoEXP>>> get() = _filteredDataFlowEXP

    val _filteredData =  MutableStateFlow<ApiResult<List<Any>>>(ApiResult.Loading)
    val filteredData: StateFlow<ApiResult<List<Any>>> get() = _filteredData

    fun filterData(selectDay: String, selectWeek: String) {
        var dayOfWeek = selectDay
        var numOfWeek = selectWeek
        viewModelScope.launch {
            settingsDataStore.selectedItemFlow.collectLatest {
                when(it) {
                    0 -> {
                        _exampleFlowTestEXP.collectLatest { apiResult ->
                            when(apiResult) {
                                is ApiResult.Success -> {
                                    val filteredData = apiResult.data.filter { table ->
                                        table.day == dayOfWeek
                                    }
                                    _filteredDataFlowEXP.value = ApiResult.Success(filteredData)
                                    Log.e("ApiResult2", _filteredDataFlowEXP.value.toString())
                                }

                                is ApiResult.Error -> {
                                    // Оставляем ошибку без изменений и сохраняем Loading в отфильтрованных данных
                                    _filteredDataFlowEXP.value = ApiResult.Loading
                                }
                                ApiResult.Loading -> {
                                    // Оставляем состояние загрузки без изменений
                                    _filteredDataFlowEXP.value = ApiResult.Loading
                                }
                            }
                        }
                    }
                    1 -> {
                        _exampleFlowTestEXP.collectLatest { apiResult ->
                            when(apiResult) {
                                is ApiResult.Success -> {
                                    val filteredData = apiResult.data.filter { table ->
                                        table.day == dayOfWeek
                                    }
                                    _filteredDataFlowEXP.value = ApiResult.Success(filteredData)
                                    Log.e("ApiResult2", _filteredDataFlowEXP.value.toString())
                                }

                                is ApiResult.Error -> {
                                    // Оставляем ошибку без изменений и сохраняем Loading в отфильтрованных данных
                                    _filteredDataFlowEXP.value = ApiResult.Loading
                                }
                                ApiResult.Loading -> {
                                    // Оставляем состояние загрузки без изменений
                                    _filteredDataFlowEXP.value = ApiResult.Loading
                                }
                            }
                        }
                    }
                    2 -> {
                        _exampleFlowTestEXP.collectLatest { apiResult ->
                            when(apiResult) {
                                is ApiResult.Success -> {
                                    val filteredData = apiResult.data.filter { table ->
                                        table.day == dayOfWeek
                                    }
                                    _filteredDataFlowEXP.value = ApiResult.Success(filteredData)
                                    Log.e("ApiResult2", _filteredDataFlowEXP.value.toString())
                                }

                                is ApiResult.Error -> {
                                    // Оставляем ошибку без изменений и сохраняем Loading в отфильтрованных данных
                                    _filteredDataFlowEXP.value = ApiResult.Loading
                                }
                                ApiResult.Loading -> {
                                    // Оставляем состояние загрузки без изменений
                                    _filteredDataFlowEXP.value = ApiResult.Loading
                                }
                            }
                        }
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
            when(selectedItem) {
                0 -> {
                    repository.getSchedule2111YEXP().collectLatest { data ->
                        _exampleFlowTestEXP.update { data }
                    }

                }
                1 -> {
                    repository.getSchedule2111YEXP().collectLatest { data ->
                        _exampleFlowTestEXP.update { data }
                    }
                }
                2 -> {
                    repository.getSchedule2111YEXP().collectLatest { data ->
                        _exampleFlowTestEXP.update { data }
                    }
                }
            }
//            connectivityObserver.observe().collectLatest { status->
//                when(status) {
//                    ConnectivityManager.Status.Available -> {
//
//                    }
//                    ConnectivityManager.Status.Losing -> {
//
//                    }
//                    ConnectivityManager.Status.Lost -> {
//
//                    }
//                    ConnectivityManager.Status.Unavailable -> {
//
//                    }
//                }
//            }
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
}