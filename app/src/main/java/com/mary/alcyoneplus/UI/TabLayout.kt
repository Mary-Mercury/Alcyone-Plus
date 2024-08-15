package com.mary.alcyoneplus.UI

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.mary.alcyoneplus.R
import com.mary.alcyoneplus.UI.Screens.FirstTab
import com.mary.alcyoneplus.UI.Screens.SecondTab

@Composable
fun TabLayout() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf(stringResource(R.string.firstSubGroup), stringResource(R.string.secondSubGroup))

    Column {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = title) }
                )
            }
        }
        when (selectedTabIndex) {
            0 -> FirstTab()
            1 -> SecondTab()
        }
    }
}