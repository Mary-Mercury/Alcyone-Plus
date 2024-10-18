package com.mary.alcyoneplus.UI.Screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mary.alcyoneplus.UI.BottomBar
import com.mary.alcyoneplus.UI.NavigationGraph
import com.mary.compose.AlcyonePlusTheme

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