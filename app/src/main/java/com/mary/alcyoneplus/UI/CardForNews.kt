package com.mary.alcyoneplus.UI

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mary.compose.AlcyonePlusTheme

data class News(val title: String, val content: String)

@Composable
fun NewsCard(title: String, content: String) {

    val expanded = remember { mutableStateOf(false) }
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
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = content,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
                    .clickable {
                        expanded.value = !expanded.value
                    },
                maxLines = if(expanded.value) 10 else 2,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AlcyonePlusTheme {
        NewsCard(
            title = "Обновление расписания для группы 3842.9",
            content = "Было обновлено расписание для группы 3842.9, изменения затронули следующие дни: Понедельник, Среду, Пятницу и Субботу."
        )
    }
}