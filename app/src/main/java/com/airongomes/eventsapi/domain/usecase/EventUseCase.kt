package com.airongomes.eventsapi.domain.usecase

import com.airongomes.eventsapi.domain.repository.EventRepository

class EventUseCase(
    private val repository: EventRepository
) {
    suspend fun getEventList() = repository.getEventList()
}