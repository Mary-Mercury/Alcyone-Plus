package com.mary.alcyoneplus.UI

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mary.alcyoneplus.Data.TableTestDto
import com.mary.compose.AlcyonePlusTheme

@Composable
fun ScheduleCard(tableTestDto: TableTestDto?) {

    val state = remember {
        MutableTransitionState(false).apply {
            targetState = false
        }
    }

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
                .clickable {
                    state.targetState = !state.targetState
                }
        ) {
            Text(
                text = tableTestDto?.SubName ?: "Null",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp)
            )
            Column(
                modifier = Modifier

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = tableTestDto?.time ?: "Null",
                        fontSize = 11.sp,
                        modifier = Modifier
                            .padding(start = 5.dp, end = 15.dp)
                    )
                    Text(
                        text = tableTestDto?.AudName ?: "Null",
                        fontSize = 11.sp,
                        modifier = Modifier
                            .padding(start = 5.dp, end = 15.dp)
                    )
                    Text(
                        text = if (tableTestDto?.type !=null) tableTestDto.type else "нд",
                        fontSize = 11.sp,
                        modifier = Modifier
                            .padding(start = 5.dp, end = 5.dp)
                    )
                    Text(
                        text = "нажмите для доп. информации",
                        fontSize = 11.sp,
                        modifier = Modifier
                            .padding(start = 5.dp, end = 5.dp),
                        textAlign = TextAlign.Right
                    )
                }
            }
            AnimatedVisibility(visibleState = state) {
                Text(
                    text = "Примечание от администратора: ${tableTestDto?.note ?: ""}",
                    fontSize = 11.sp,
                    modifier = Modifier
                        .padding(start = 5.dp, end = 15.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ScheduleCardPreview() {
    AlcyonePlusTheme {
        ScheduleCard(tableTestDto = null)
    }

}