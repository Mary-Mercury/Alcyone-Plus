package com.mary.alcyoneplus.UI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
fun NewsScreen() {

    val newsList = listOf(
        News("Обновление расписания для группы 3842.9", "Было обновлено расписание для группы 3842.9, изменения затронули следующие дни: Понедельник, Среду, Пятницу и Субботу."),
        News("Обновление расписания для группы 3842.9", "Было обновлено расписание для группы 3842.9, изменения затронули следующие дни: Понедельник, Среду, Пятницу и Субботу."),
        News("Обновление расписания для группы 3842.9", "Было обновлено расписание для группы 3842.9, изменения затронули следующие дни: Понедельник, Среду, Пятницу и Субботу.")
    )
    NewsList(newsList)
}

@Composable
fun NewsList(newsList: List<News>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 6.dp, vertical = 8.dp)
    ) {
        items(newsList) { news ->
            NewsCard(title = news.title, content = news.content)
        }
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

@Preview
@Composable
fun InfoScreenPreview() {
    AlcyonePlusTheme {
        InfoScreen()
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
fun FirstTab() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(R.string.firstTab))
    }
}

@Composable
fun SecondTab() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(R.string.secondTab))
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MainScreenPreview() {
    AlcyonePlusTheme {
        MainScreen()
    }
}


