package com.airongomes.eventsapi.domain.remote.response

import com.airongomes.eventsapi.domain.model.Event
import com.google.gson.annotations.SerializedName

data class EventResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("date") val date: Long?,
    @SerializedName("price") val price: Double?,
)

fun EventResponse.toModel() = Event(
    id = id ?: 0,
    title = title ?: "",
    description = description ?: "",
    image = image ?: "",
    date = date ?: 0L,
    price = price ?: 0.0
)