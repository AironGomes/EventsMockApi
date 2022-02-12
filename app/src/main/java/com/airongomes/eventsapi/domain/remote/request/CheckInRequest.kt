package com.airongomes.eventsapi.domain.remote.request

import com.airongomes.eventsapi.domain.model.CheckIn
import com.google.gson.annotations.SerializedName

data class CheckInRequest(
    @SerializedName("eventId") val eventId: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("email") val email: String?,
)

fun CheckIn.toRequest() = CheckInRequest(
    eventId = eventId,
    name = name,
    email = email
)