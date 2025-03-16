package com.mary.alcyoneplus.UI.Screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mary.alcyoneplus.R
import com.mary.alcyoneplus.UI.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(navController: NavController, onComplete: () -> Unit) {
    val pages = listOf<@Composable () -> Unit> (
        { FistScreen() },
        { SecondScreenWithSettings() },
        { S3rdScreenEnding(
            onStartCompleted = onComplete,
            navController = navController)
        }
    )
    val pagerState = rememberPagerState(pageCount = { pages.size })

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(state = pagerState) { page ->
            pages[page]()
        }
    }
}

@Composable
fun FistScreen() {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
        ) {
            Text(
                text = "Добро пожаловать в Alcyone Plus!",
                fontSize = 30.sp,
                lineHeight = 35.sp
            )
            Spacer(modifier = Modifier.padding(3.dp))
            Text(
                text = "Прежде чем начать, давайте настроим приложение.",
                fontSize = 30.sp,
                lineHeight = 35.sp
            )
        }
    }
}

@Composable
fun SecondScreenWithSettings(viewModel: MainViewModel = hiltViewModel()) {

    val switchState by viewModel.switchState.collectAsState()
    val isDropDownExpanded = remember { mutableStateOf(false) }
    val selectedItem by viewModel.selectedItem.collectAsState()
    val usernames = listOf("3842", "2111", "2111-У")
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
        ) {
            Text(text="Выберите группу для получения расписания: ",
                fontSize = 30.sp,
                lineHeight = 35.sp)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        isDropDownExpanded.value = true
                    }
            ) {
                Row {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = usernames[selectedItem], fontSize = 25.sp)
                    Image(
                        painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                        contentDescription = "DropDown Icon"
                    )
                }
                DropdownMenu(
                    modifier = Modifier.fillMaxWidth(),
                    expanded = isDropDownExpanded.value,
                    onDismissRequest = {
                        isDropDownExpanded.value = false
                    }) {
                    usernames.forEachIndexed { index, username ->
                        DropdownMenuItem(text = {
                            Text(text = username)
                        },
                            onClick = {
                                isDropDownExpanded.value = false
                                viewModel.saveSelectedItem(index)
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text="Включить инверсию недели?",
                    fontSize = 30.sp,
                    lineHeight = 35.sp)
                Switch(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    checked = switchState,
                    onCheckedChange = { isEnabled ->
                        viewModel.saveSwitchState(isEnabled)
                    }
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                fontSize = 15.sp,
                text = "Эти настройки можно будет изменить в любой момент времени в самом приложении!")
        }
    }
}


@Composable
fun S3rdScreenEnding(onStartCompleted: () -> Unit, navController: NavController) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
        ) {
            Text(
                text="Настройки успешно применены!",
                fontSize = 30.sp,
                lineHeight = 35.sp)
            Spacer(modifier = Modifier.padding(3.dp))
            Text(
                text = "Приятного пользования!",
                fontSize = 30.sp,
                lineHeight = 35.sp)
        }
        Button(
            onClick = {
                onStartCompleted()
                      navController.navigate("final")},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Перейти к приложению ->")
        }
    }
}

