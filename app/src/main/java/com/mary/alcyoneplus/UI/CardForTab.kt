package com.mary.alcyoneplus.UI

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mary.compose.AlcyonePlusTheme

@Composable
fun ScheduleCard2(
    schedule: String,
    time: String,
    auditory: String,
    type: String?,
    note: String?
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { expanded = !expanded },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = schedule,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = time,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = auditory,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = type?: "null",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
//                Text(
//                    text = "Аль Ханани",
//                    style = MaterialTheme.typography.bodyMedium,
//                    modifier = Modifier.weight(1f)
//                )
            }
            if (expanded) {
                Divider(modifier = Modifier.padding(vertical = 4.dp))
                Text(
                    text = note?: "null",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp)
                )
            } else {
                Text(
                    text = "нажмите для доп. информации",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ScheduleCardPreview() {
    AlcyonePlusTheme {
        ScheduleCard2("Моделирование систем", "10:40", "У-512", "Лекция", "")
    }
}