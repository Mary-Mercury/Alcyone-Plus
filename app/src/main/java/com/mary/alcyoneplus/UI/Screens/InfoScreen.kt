package com.mary.alcyoneplus.UI.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mary.alcyoneplus.R

@Composable
fun InfoScreen() {
    val scrollState = rememberScrollState()

    Surface {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
//                    .padding(16.dp)
                .absolutePadding(top = 20.dp, left = 6.dp, right = 6.dp)
        ) {


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
            Divider(modifier = Modifier.padding(vertical = 8.dp))

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