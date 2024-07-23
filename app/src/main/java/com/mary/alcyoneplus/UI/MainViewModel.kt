package com.mary.alcyoneplus.UI

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
import androidx.compose.runtime.State
import com.mary.alcyoneplus.Data.TableTestDto


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: repository
): ViewModel() {

    //для получения данных для новостей
    private val _news = MutableStateFlow<ApiResult<List<NewsDto>>>(ApiResult.Loading)
    val news: StateFlow<ApiResult<List<NewsDto>>> get() = _news

    //для получения расписания
    private val _exampleFlowTest = MutableStateFlow<ApiResult<List<TableTestDto>>>(ApiResult.Loading)
    val exampleFlowTest: StateFlow<ApiResult<List<TableTestDto>>> get() = _exampleFlowTest

    //для обмена данных выбранного дня
    private val _selectedDay = mutableStateOf<String>("")
    val selectedDay: State<String> get() = _selectedDay
    val Day: String = selectedDay.value

    //для обмена данных выбранной недели
    private val _selectedWeek = mutableStateOf<String>("")
    val selectedWeek: State<String> get() = _selectedWeek
    val dayOfWeek: String = selectedWeek.value



    //фильтрованные данные
    private val _filteredDataFlow = MutableStateFlow<ApiResult<List<TableTestDto>>>(ApiResult.Loading)
    val filteredDataFlow: StateFlow<ApiResult<List<TableTestDto>>> get() = _filteredDataFlow

    fun filterData(selectDay: String, selectWeek: String) {
        var dayOfWeek = selectDay
        Log.e("ApiResult", dayOfWeek)
        var numOfWeek = selectWeek
        Log.e("ApiResult", numOfWeek)
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
        fetchExampleFlowTest()
    }

    fun fetchNews() {
        viewModelScope.launch {
            repository.getNews().collectLatest { data ->
                _news.update { data }
                Log.e("TAGss", "getNews: $_news")
            }
        }
    }

    fun fetchExampleFlowTest() {
        viewModelScope.launch {
            repository.getExampleFlowTest().collectLatest { data ->
                _exampleFlowTest.update { data }
            }
        }
    }
}