package com.mary.alcyoneplus.UI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mary.compose.AlcyonePlusTheme

data class SettingsInfo(
    var title: String,
    var content: String
)

@Composable
fun SettingsInfoCard(title: String, content: String) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Text(
//                text = title,
//                style = MaterialTheme.typography.titleSmall,
//                fontSize = 20.sp,
//                modifier = Modifier.padding(bottom = 8.dp)
//            )
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 8.dp),
//            ) {
//                Text(
//                    text = content,
//                    style = MaterialTheme.typography.bodySmall,
//                    fontSize = 16.sp,
//                    modifier = Modifier.padding(16.dp)
//                )
//            }
//        }
//    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = content,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 16.sp,
                modifier = Modifier.padding(6.dp)
            )
        }
    }
}

@Preview
@Composable
fun SettingsInfoCardPreview() {
    AlcyonePlusTheme {
        SettingsInfoCard(title = "Title", content = "Content")
    }
}