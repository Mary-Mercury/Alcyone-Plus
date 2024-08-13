package com.mary.alcyoneplus.UI

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
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
import kotlinx.coroutines.launch

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
fun appDrawer() {
    Column {
        Text("Menu Item 1")
        Text("Menu Item 2")
        Text("Menu Item 3")
    }
}

//@Composable
//fun testMainScreen() {
//    val navController = rememberNavController()
//    val drawerState = rememberDrawerState(DrawerValue.Closed)
//    val coroutineScope = rememberCoroutineScope()
//
//    ModalNavigationDrawer(
//        drawerState = drawerState,
//        drawerContent = {
//            NavigationDrawerContent(
//                onDestinationClicked = { route ->
//                    navController.navigate(route) {
//                        // Ensure that only one instance of the screen is in the backstack
//                        popUpTo(navController.graph.findStartDestination().id) {
//                            saveState = true
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//                    coroutineScope.launch { drawerState.close() }
//                }
//            )
//        }
//    ) {
//        Scaffold(
//            bottomBar = {
//                BottomNavigationBar(navController = navController)
//            }
//        ) { innerPadding ->
//            NavHost(
//                navController = navController,
//                startDestination = "home",
//                modifier = Modifier.padding(innerPadding)
//            ) {
//                composable("home") { HomeScreen() }
//                composable("profile") { ProfileScreen() }
//                composable("settings") { SettingsScreen() }
//            }
//        }
//    }
//}

@Composable
fun hostScreen() {
    MainScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun navigationDrawer() {
    ///List of Navigation Items that will be clicked
    val items = listOf(
        NavigationItems(
            title = "Main",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        NavigationItems(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings
        ),
        NavigationItems(
            title = "Faq",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
            badgeCount = 105
        )
    )

    //Remember Clicked item state
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

//Remember the State of the drawer. Closed/ Opened
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.title) },
                        selected = index == selectedItemIndex,
                        onClick = {
//                              navController.navigate(item.route)

                            selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                        badge = {
                            item.badgeCount?.let {
                                Text(text = item.badgeCount.toString())
                            }
                        },
//                        modifier = Modifier
//                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }

            }
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Alcyone Plus")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                when (selectedItemIndex) {
                    0 -> MainScreen()
                    1 -> popScreen()
                    2 -> popScreen2()
                }
            }
        }
    }
}

@Composable
fun popScreen() {
    Text(text = "popScreen")
}

@Composable
fun popScreen2() {
    Text(text = "popScreen2")
}

@Composable
fun popScreen3() {
    Text(text = "popScreen3")
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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


