package com.airongomes.eventsapi

import com.airongomes.eventsapi.domain.model.CheckIn
import com.airongomes.eventsapi.domain.model.Event

val fakeEvent = Event(
    id = 1,
    title = "Fake title",
    description = "Fake description",
    image = "",
    date = 1534784400000L,
    price = 0.00
)

val fakeCheckIn = CheckIn(
    eventId = 1,
    name = "Fake Name",
    email = "email.fake@gmail.com"
)