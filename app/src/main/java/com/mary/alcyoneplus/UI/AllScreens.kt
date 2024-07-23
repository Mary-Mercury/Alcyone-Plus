package com.mary.alcyoneplus.UI

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mary.alcyoneplus.Data.ApiResult
import com.mary.alcyoneplus.Data.NewsDto
import com.mary.alcyoneplus.Data.TableTestDto
import com.mary.alcyoneplus.R
import com.mary.compose.AlcyonePlusTheme

@Composable
fun HomeScreen() {

    Column {
        CalendarApp()
        TabLayout()
    }
}

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

@Composable
fun LoadingView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}


@Composable
fun InfoScreen() {
    var additionalSwitch by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()


    Surface {
        Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.alternativeModeTitle),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,

                        )
                    Switch(
                        checked = additionalSwitch,
                        onCheckedChange = { additionalSwitch = it }
                    )
                }
                Text(
                    text = stringResource(R.string.alternativeMode),
                    fontSize = 16.sp)
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // Text sections
                TextSection(
                    title = stringResource(R.string.howItWorkTitle),
                    content = stringResource(R.string.howItWork)
                )
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                TextSection(
                    title = stringResource(R.string.aboutAppTitle),
                    content = stringResource(R.string.aboutApp)
                )
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                TextSection(
                    title = stringResource(R.string.aboutMeTitle),
                    content = stringResource(R.string.aboutMe)
                )
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                TextSection(
                    title = stringResource(R.string.contactTitle),
                    content = stringResource(R.string.contact)
                )
        }
    }
}


@Composable
fun TextSection(title: String, content: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = content, fontSize = 16.sp)
    }
}

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

@Composable
fun MainScreen() {
    val navController: NavHostController = rememberNavController()

    val buttonsVisible = remember { mutableStateOf(true) }

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                state = buttonsVisible,
                modifier = Modifier
            )
        }) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            NavigationGraph(navController = navController)
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun MainScreenPreviewNight() {
    AlcyonePlusTheme {
        MainScreen()
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun MainScreenPreview() {
    AlcyonePlusTheme {
        MainScreen()
    }
}


