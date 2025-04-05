package com.mary.alcyoneplus.Data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleOfflineRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val fileName = "ScheduleOffline"

    private val json = Json { prettyPrint = true }

    private fun getFile(): File {
        return File(context.filesDir, fileName)
    }

    fun getAll(): List<scheduleOfflineDto> {
        val file = getFile()
        if (!file.exists()) return emptyList()
        val content = file.readText()
        return if (content.isNotBlank()) {
            json.decodeFromString(content)
        } else emptyList()
    }

    fun replaceAll(newSchedule: List<scheduleOfflineDto>) {
        saveAll(newSchedule)
    }

    private fun saveAll(schedule: List<scheduleOfflineDto>) {
        val jsonString = json.encodeToString(schedule)
        getFile().writeText(jsonString)
    }
}