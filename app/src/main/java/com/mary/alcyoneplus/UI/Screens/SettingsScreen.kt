package com.mary.alcyoneplus.UI.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mary.alcyoneplus.R
import com.mary.alcyoneplus.UI.MainViewModel
import com.mary.compose.AlcyonePlusTheme

@Composable
fun SettingsScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val switchState by viewModel.switchState.collectAsState()

    val isDropDownExpanded = remember {
        mutableStateOf(false)
    }

    val selectedItem by viewModel.selectedItem.collectAsState()


    val usernames = listOf("3842", "2111")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .absolutePadding(top = 20.dp, left = 6.dp, right = 6.dp)
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
                checked = switchState,
                onCheckedChange = { isEnabled ->
                    viewModel.saveSwitchState(isEnabled)
                }
            )
        }
        Text(
            text = stringResource(R.string.alternativeMode),
            fontSize = 16.sp)

        Divider(modifier = Modifier.padding(vertical = 8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text =("Select Group"),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Column(
                    modifier = Modifier.absolutePadding(right = 6.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.clickable {
                            isDropDownExpanded.value = true
                        }
                    ) {
                        Text(text = usernames[selectedItem])
                    }
                    DropdownMenu(
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
            }
            Text(
                text = ("Select your study group to get the current schedule."),
                fontSize = 16.sp)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
        }
    }

@Preview(
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun SettingsPreview() {
    AlcyonePlusTheme {
        SettingsScreen()
    }
}