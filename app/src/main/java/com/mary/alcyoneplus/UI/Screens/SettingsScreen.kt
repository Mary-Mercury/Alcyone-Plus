package com.mary.alcyoneplus.UI.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mary.alcyoneplus.R

@Composable
fun SettingsScreen(
) {
    var additionalSwitch by remember { mutableStateOf(false) }

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
                checked = additionalSwitch,
                onCheckedChange = { additionalSwitch = it }
            )
        }
        Text(
            text = stringResource(R.string.alternativeMode),
            fontSize = 16.sp)
        Divider(modifier = Modifier.padding(vertical = 8.dp))
    }
}