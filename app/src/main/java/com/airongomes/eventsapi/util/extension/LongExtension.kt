package com.airongomes.eventsapi.util.extension

import android.text.format.DateFormat
import java.util.*

fun Long.toDate(): String =
    DateFormat.format("d MMM yyyy", this.getCalendar()).toString()

fun Long.toDateTime(): String =
    DateFormat.format("d MMM yyyy 'às' h:mm a", this.getCalendar()).toString()


fun Long.getCalendar(): Calendar {
    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.timeInMillis = this
    return calendar
}