package com.airongomes.eventsapi.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val date: Long,
    val price: Double
): Parcelable