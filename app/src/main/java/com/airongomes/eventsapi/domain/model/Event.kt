package com.airongomes.eventsapi.domain.model

data class Event(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val date: Long,
    val price: Double
)