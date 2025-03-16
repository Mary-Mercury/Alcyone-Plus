package com.mary.alcyoneplus.UI

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mary.alcyoneplus.Data.ScheduleDtoEXP

@Composable
fun ScheduleCardEXP(
    schedule: String,
    time: String,
    auditory: String,
    type: String?,
    note: String?
) {

    val state = remember {
        MutableTransitionState(false).apply {
            targetState = false
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(3.dp)
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
                text = schedule,
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
                        .wrapContentHeight()
                ) {
                    Text(
                        text = time,
                        fontSize = 11.sp,
                        modifier = Modifier
                            .padding(start = 5.dp, end = 15.dp)
                    )
                    Text(
                        text = auditory,
                        fontSize = 11.sp,
                        modifier = Modifier
                            .padding(start = 5.dp, end = 15.dp)
                    )
                    Text(
                        text = type ?: "null",
                        fontSize = 11.sp,
                        modifier = Modifier
                            .padding(start = 5.dp, end = 5.dp)
                    )
                }
                Row {
                    Text(
                        text = "нажмите для доп. информации",
                        fontSize = 11.sp,
                        modifier = Modifier
                            .padding(start = 5.dp, end = 5.dp)
                            .weight(1f),
                        textAlign = TextAlign.Right
                    )
                }
            }
            AnimatedVisibility(visibleState = state) {
                Text(
                    text = "Примечание от администратора: $note",
                    fontSize = 11.sp,
                    modifier = Modifier
                        .padding(start = 5.dp, end = 15.dp)
                )
            }
        }
    }
}