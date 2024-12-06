package com.mary.alcyoneplus.UI

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mary.alcyoneplus.Data.userState
import com.mary.alcyoneplus.UI.Screens.LogRegScreen
import com.mary.alcyoneplus.UI.Screens.NavigationDrawer

//@Composable
//fun mainHost() {
//    val navController = rememberNavController()
//    val viewModel: MainViewModel = hiltViewModel()
//    val userState by viewModel.userStateLD
//
//
//    NavHost(navController = navController, startDestination = "firstScreen") {
//        composable("firstScreen") { LogRegScreen(navController) }
//        composable("secondScreen") { NavigationDrawer(navController = navController) }
//    }
//    var token = viewModel.getToken(context = LocalContext.current)
//
//    if (token.isNullOrEmpty()) {
//        LogRegScreen(navController)
//    } else {
//        NavigationDrawer(navController)
//    }
//
//    when (userState) {
//        is userState.Loading -> {
//            // Показываем индикатор загрузки или заглушку
//        }
//
//        is userState.Success -> {
//            // Выполняем переход на домашний экран при успешной авторизации
//            LaunchedEffect(Unit) {
//                navController.navigate("secondScreen") {
//                    popUpTo("firstScreen") { inclusive = true }
//                }
//            }
//        }
//
//
//        is userState.Error -> {
//            // Показ сообщения об ошибке или соответствующего UI
//            val errorMessage = (userState as userState.Error).message
//            // Отображение текста ошибки
//        }
//    }
//}