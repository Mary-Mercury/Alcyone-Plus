package com.mary.alcyoneplus.UI.Screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.mary.alcyoneplus.Data.ApiResult
import com.mary.alcyoneplus.Data.NewsDto
import com.mary.alcyoneplus.UI.MainViewModel
import com.mary.alcyoneplus.UI.NewsCard

@Composable
fun NewsScreen(viewModel: MainViewModel = hiltViewModel()) {
    val newsState by viewModel.news.collectAsState()


    when (newsState) {
        is ApiResult.Loading -> {
            LoadingView()
        }
        is ApiResult.Error -> {
//            Text("Error: ${(newsState as ApiResult.Error).message}")
            Text(text = "An error occurred")
        }
        is ApiResult.Success -> {
            val newsList = (newsState as ApiResult.Success<List<NewsDto>>).data
            LazyColumn {
                items(newsList) { news ->
                    NewsCard(news)
                }
            }
        }
    }
}