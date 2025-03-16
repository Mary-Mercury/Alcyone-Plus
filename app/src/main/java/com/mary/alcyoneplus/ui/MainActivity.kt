package com.mary.alcyoneplus.UI

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mary.alcyoneplus.UI.Screens.NavigationDrawer
import com.mary.alcyoneplus.UI.Screens.WelcomeScreen
import com.mary.compose.AlcyonePlusTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity: ComponentActivity () {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlcyonePlusTheme {
                Navigation()
            }
        }
    }
}


@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = hiltViewModel()
    val isFirstLaunch = viewModel.isFirstLaunch
    val initialScreen = if(!isFirstLaunch) { "onboarding" } else { "final" }

    NavHost(
        navController = navController,
        startDestination = initialScreen
    ) {
        composable("onboarding") { WelcomeScreen(navController)
            { viewModel.saveFirstLaunch("saveFirstLaunch",true)  } }
        composable("final") { NavigationDrawer(navController) }
    }
}