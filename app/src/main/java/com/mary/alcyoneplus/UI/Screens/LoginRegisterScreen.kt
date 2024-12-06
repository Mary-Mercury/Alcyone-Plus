package com.mary.alcyoneplus.UI.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mary.alcyoneplus.UI.MainViewModel

@Composable
fun LogRegScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(true) }
    var showErrorEmail by remember { mutableStateOf(false) }
    var showErrorPassword by remember { mutableStateOf(false) }

    var currentUserState by remember { mutableStateOf("") }

    Surface(
        color = MaterialTheme.colorScheme.background,
    ) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = userEmail,
                isError = showErrorEmail,
                placeholder = {
                    Text(text = "email")
                },
                onValueChange = {
                    userEmail = it
                })
            if (showErrorEmail) {
                Text(text = "email format", color = Color.Red)
            }

            Spacer(modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = userPassword,
                placeholder = {
                    Text(text = "password")
                },
                visualTransformation = if (showPassword) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(
                            imageVector = if (showPassword) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = if (showPassword) "Скрыть пароль" else "Показать пароль"
                        )
                    }
                },
                isError = showErrorPassword,
                onValueChange = {
                    userPassword = it
                }
            )
            if (showErrorPassword) {
                Text(text = "длиннее 6", color = Color.Red)
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                onClick = {
                    val isValidEmail =
                        android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()
                    val isValidPassword = userPassword.length >= 6

                    if (!isValidEmail) {
                        showErrorEmail = true
                    } else {
                        showErrorEmail = false
                    }
                    if (!isValidPassword) {
                        showErrorPassword = true
                    } else {
                        showErrorPassword = false
                    }
                    if (isValidEmail && isValidPassword) {
                        viewModel.login(
                            context,
                            userEmail,
                            userPassword,
                        )
                    }

                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "логин")
            }
        }
        Column {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "",
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "",
                textAlign = TextAlign.Center
            )
            Button(
                onClick = {
                    val isValidEmail =
                        android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()
                    val isValidPassword = userPassword.length >= 6

                    if (!isValidEmail) {
                        showErrorEmail = true
                    } else {
                        showErrorEmail = false
                    }
                    if (!isValidPassword) {
                        showErrorPassword = true
                    } else {
                        showErrorPassword = false
                    }
                    if (isValidEmail && isValidPassword) {
                        viewModel.signUp(
                            context,
                            userEmail,
                            userPassword,
                        )
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "регистрация")
            }
            Button(
                onClick = {
                    viewModel.anonymousLogin(
                        context
                    )
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "регистрация как аноним")
            }
            val mContext = LocalContext.current

//            when (userState) {
//                is userState.Loading -> {
//                }
//
//                is userState.Success -> {
//                    val message = (userState as userState.Success).message
//                    currentUserState = message
//
//                    val fragmentManager = requireActivity().supportFragmentManager
//                    val secondFragment = BasicFragment()
//                    val transaction = fragmentManager.beginTransaction()
//                    transaction.replace(R.id.fragment_container, secondFragment)
//                    transaction.addToBackStack(null)
//                    transaction.commit()
//
//                }
//
//                is userState.Error -> {
//                    val message = (userState as userState.Error).message
////                    currentUserState = message
//                    currentUserState = "error"
//                }
//
//                else -> {}
//            }

//            when (userState) {
//                is userState.Loading -> {
//                    // Показываем индикатор загрузки или заглушку
//                }
//
//                is userState.Success -> {
//                    // Выполняем переход на домашний экран при успешной авторизации
//                    LaunchedEffect(Unit) {
//                        navController.navigate("secondScreen") {
//                            popUpTo("firstScreen") { inclusive = true }
//                        }
//                    }
//                }
//
//
//                is userState.Error -> {
//                    // Показ сообщения об ошибке или соответствующего UI
//                    val errorMessage = (userState as userState.Error).message
//                    // Отображение текста ошибки
//                }
//            }
//
//            if (currentUserState.isNotEmpty()) {
//                Text(text = currentUserState)
//            }
        }
    }
        }
}