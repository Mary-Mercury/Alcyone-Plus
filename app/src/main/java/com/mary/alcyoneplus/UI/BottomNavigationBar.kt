package com.mary.alcyoneplus.UI

import androidx.compose.runtime.getValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mary.alcyoneplus.R


sealed class Destinations(
    val route: String,
    var title: String? = null,
    val icon: ImageVector? = null
) {
    object HomeScreen : Destinations(
        route = "home_screen",
//        title = "Main",
        icon = Icons.Default.Home
    )

    object News : Destinations(
        route = "news_screen",
//        title = "News",
        icon = Icons.Default.Email
    )

    object Info : Destinations(
        route = "settings_screen",
//            title = "Info",
        icon = Icons.Default.Info
    )
}



@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Destinations.HomeScreen.route) {
        composable(Destinations.HomeScreen.route) {
            HomeScreen()
        }
        composable(Destinations.News.route) {
            NewsScreen()
        }
        composable(Destinations.Info.route) {
            InfoScreen()
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController, state: MutableState<Boolean>, modifier: Modifier = Modifier
) {

    val screens = listOf(
        Destinations.HomeScreen, Destinations.News, Destinations.Info
    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->
            for (i in screens) {
                if (i.route == "home_screen") {
                    i.title = stringResource(R.string.homeScreenBtmNav)
                }
                if (i.route == "news_screen") {
                    i.title = stringResource(R.string.newsScreenBtmNav)
                }
                if (i.route == "settings_screen") {
                    i.title = stringResource(R.string.infoScreenBtmNav)
                }
            }

            NavigationBarItem(
                label = {
                    Text(text = screen.title!!)
                },
                icon = {
                    Icon(imageVector = screen.icon!!, contentDescription = "")
                },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

