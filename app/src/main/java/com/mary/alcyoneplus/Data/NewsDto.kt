package com.mary.alcyoneplus.Data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsDto(

    @SerialName("id") val id: Int,
    @SerialName("Title") val title: String,
    @SerialName("Content") val content: String,
    @SerialName("created_at") val createdAt: String,
)
