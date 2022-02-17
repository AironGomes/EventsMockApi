package com.airongomes.eventsapi.domain.usecase

import com.airongomes.eventsapi.domain.model.Event
import com.airongomes.eventsapi.domain.remote.NetworkResult
import com.airongomes.eventsapi.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow

interface FetchEventsUseCase {
    suspend operator fun invoke(): Flow<NetworkResult<List<Event>>>
}

class FetchEventsUseCaseImpl(
    private val repository: EventRepository
) : FetchEventsUseCase {

    override suspend fun invoke() = repository.getEventList()

}