package com.mary.alcyoneplus.Data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class scheduleOfflineDto(
    val id: Int,
    val time: String,
    val day: String,
    val subgroup: String,
    val subNameEven: String,
    val audNameEven: String,
    val typeEven: String? = null,
    val unSubNameEven: String,
    val unAudNameEven: String,
    val typeUneven: String? = null,
    val noteEven: String? = null,
    val noteUneven: String? = null,
)