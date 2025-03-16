package com.mary.alcyoneplus.Data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleDtoEXP(
    @SerialName("id") val id: Int,
    @SerialName("time") val time: String,
    @SerialName("day") val day: String,
    @SerialName("subgroup") val subgroup: String,
    @SerialName("sub_name_even") val subNameEven: String,
    @SerialName("aud_name_even") val audNameEven: String,
    @SerialName("type_even") val typeEven: String? = null,
    @SerialName("sub_name_uneven") val unSubNameEven: String,
    @SerialName("aud_name_uneven") val unAudNameEven: String,
    @SerialName("type_uneven") val typeUneven: String? = null,
    @SerialName("note_even") val noteEven: String? = null,
    @SerialName("note_uneven") val noteUneven: String? = null,
)
