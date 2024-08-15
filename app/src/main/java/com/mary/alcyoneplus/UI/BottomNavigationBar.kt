package com.mary.alcyoneplus.UI

import androidx.compose.runtime.getValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
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
import com.mary.alcyoneplus.UI.Screens.HomeScreen
import com.mary.alcyoneplus.UI.Screens.NewsScreen


sealed class Destinations(
    val route: String,
    var title: String? = null,
    val icon: ImageVector? = null
) {

    object HomeScreen : Destinations(
        route = "home_screen",
//        title = stringResource(id = R.string.homeScreen),
        icon = Icons.Default.DateRange
    )

    object News : Destinations(
        route = "news_screen",
//        title = "News",
        icon = Icons.Default.Email
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

    }
}

@Composable
fun BottomBar(
    navController: NavHostController, state: MutableState<Boolean>, modifier: Modifier = Modifier
) {

    val screens = listOf(
        Destinations.HomeScreen, Destinations.News,
    )
    NavigationBar(

    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->
            for (i in screens) {
                if (i.route == "home_screen") {
                    i.title = stringResource(R.string.homeScreen)
                }
                if (i.route == "news_screen") {
                    i.title = stringResource(R.string.newsScreenBtmNav)
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

