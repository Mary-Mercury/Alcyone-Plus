package com.mary.alcyoneplus.UI.Screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.mary.alcyoneplus.Data.ApiResult
import com.mary.alcyoneplus.Data.TableTestDto
import com.mary.alcyoneplus.UI.MainViewModel
import com.mary.alcyoneplus.UI.ScheduleCard

@Composable
fun FirstTab(
    viewModel: MainViewModel = hiltViewModel()
) {
    val newsState by viewModel.filteredDataFlow.collectAsState()

    when (newsState) {
        is ApiResult.Loading -> {
            LoadingView()
        }
        is ApiResult.Error -> {
//            Text("Error: ${(newsState as ApiResult.Error).message}")
            Text(text = "An error occurred")
        }
        is ApiResult.Success -> {
            val newsList = (newsState as ApiResult.Success<List<TableTestDto>>).data
            val lastSixItems = newsList.take(6)
            LazyColumn {
                items(lastSixItems) { news ->
                    ScheduleCard(news)
                }
            }
        }
    }
}

@Composable
fun SecondTab(
    viewModel: MainViewModel = hiltViewModel()
) {
    val newsState by viewModel.filteredDataFlow.collectAsState()

    when (newsState) {
        is ApiResult.Loading -> {
            LoadingView()
        }
        is ApiResult.Error -> {
//            Text("Error: ${(newsState as ApiResult.Error).message}")
            Text(text = "An error occurred")
        }
        is ApiResult.Success -> {
            val newsList = (newsState as ApiResult.Success<List<TableTestDto>>).data
            val lastSixItems = newsList.takeLast(6)
            LazyColumn {
                items(lastSixItems) { news ->
                    ScheduleCard(news)
                }
            }
        }
    }
}