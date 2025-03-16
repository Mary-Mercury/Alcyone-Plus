package com.mary.alcyoneplus.UI.Screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.mary.alcyoneplus.Data.ApiResult
import com.mary.alcyoneplus.Data.ScheduleDtoEXP
import com.mary.alcyoneplus.Data.TableTestDto
import com.mary.alcyoneplus.UI.MainViewModel
import com.mary.alcyoneplus.UI.ScheduleCard
import com.mary.alcyoneplus.UI.ScheduleCardEXP

@Composable
fun FirstTab(
    viewModel: MainViewModel = hiltViewModel()
) {
    val selectedWeek by viewModel.selectWeek
    val newsState by viewModel.filteredDataFlow.collectAsState()
    val scheduleEXP by viewModel.filteredDataFlowEXP.collectAsState()

    when (scheduleEXP) {
        is ApiResult.Loading -> {
            LoadingView()
        }
        is ApiResult.Error -> {
//            Text("Error: ${(newsState as ApiResult.Error).message}")
            Text(text = "An error occurred")
        }
        is ApiResult.Success -> {
            val newsList = (scheduleEXP as ApiResult.Success<List<ScheduleDtoEXP>>).data
            val lastSixItems = newsList.filter { it.subgroup == "1" }
            when(selectedWeek) {
                "четная" -> {
                    LazyColumn {
                        items(lastSixItems) { news ->
                            ScheduleCardEXP(
                                schedule = news.subNameEven,
                                time = news.time,
                                auditory = news.audNameEven,
                                type = news.typeEven,
                                note = news.noteEven
                            )
                        }
                    }
                }
                "нечетная" -> {
                    LazyColumn {
                        items(lastSixItems) { news ->
                            ScheduleCardEXP(
                                schedule = news.unSubNameEven,
                                time = news.time,
                                auditory = news.unAudNameEven,
                                type = news.typeUneven,
                                note = news.noteUneven
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun SecondTab(
    viewModel: MainViewModel = hiltViewModel()
) {
    val selectedWeek by viewModel.selectWeek
    val newsState by viewModel.filteredDataFlow.collectAsState()
    val scheduleEXP by viewModel.filteredDataFlowEXP.collectAsState()


    when (scheduleEXP) {
        is ApiResult.Loading -> {
            LoadingView()
        }
        is ApiResult.Error -> {
//            Text("Error: ${(newsState as ApiResult.Error).message}")
            Text(text = "An error occurred")
        }
        is ApiResult.Success -> {
            val newsList = (scheduleEXP as ApiResult.Success<List<ScheduleDtoEXP>>).data
            val lastSixItems = newsList.filter { it.subgroup == "2" }
            when(selectedWeek) {
                "четная" -> {
                    LazyColumn {
                        items(lastSixItems) { news ->
                            ScheduleCardEXP(
                                schedule = news.subNameEven,
                                time = news.time,
                                auditory = news.audNameEven,
                                type = news.typeEven,
                                note = news.noteEven
                            )
                        }
                    }
                }
                "нечетная" -> {
                    LazyColumn {
                        items(lastSixItems) { news ->
                            ScheduleCardEXP(
                                schedule = news.unSubNameEven,
                                time = news.time,
                                auditory = news.unAudNameEven,
                                type = news.typeUneven,
                                note = news.noteUneven
                            )
                        }
                    }
                }
            }

        }
    }
}