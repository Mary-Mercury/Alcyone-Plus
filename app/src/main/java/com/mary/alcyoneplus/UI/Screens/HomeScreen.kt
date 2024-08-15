package com.mary.alcyoneplus.UI.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.mary.alcyoneplus.UI.CalendarApp
import com.mary.alcyoneplus.UI.TabLayout

@Composable
fun HomeScreen() {
    Column {
        CalendarApp()
        TabLayout()
    }
}