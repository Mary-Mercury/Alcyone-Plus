package com.mary.alcyoneplus.UI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mary.alcyoneplus.Data.NewsDto
import com.mary.alcyoneplus.Data.TableTestDto
import com.mary.compose.AlcyonePlusTheme

@Composable
fun ScheduleCard(tableTestDto: TableTestDto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(3.dp), // Отступ между карточками
//        shape = RectangleShape // Устанавливаем острые углы
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 1.dp, end = 10.dp, bottom = 4.dp, top = 4.dp)
        ) {
            Text(
                text = tableTestDto.SubName,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = tableTestDto.time,
                    fontSize = 11.sp,
                    modifier = Modifier
                        .padding(start = 5.dp, end = 15.dp)
                )
                Text(
                    text = tableTestDto.AudName,
                    fontSize = 11.sp,
                    modifier = Modifier
                        .padding(start = 5.dp, end = 15.dp)
                )
            }
        }
    }
}