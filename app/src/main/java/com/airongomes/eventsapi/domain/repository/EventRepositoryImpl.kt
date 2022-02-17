package com.airongomes.eventsapi.domain.repository

import com.airongomes.eventsapi.domain.model.Event
import com.airongomes.eventsapi.domain.remote.BaseApiResponse
import com.airongomes.eventsapi.domain.remote.NetworkResult
import com.airongomes.eventsapi.domain.remote.api.EventApi
import com.airongomes.eventsapi.domain.remote.response.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


interface EventRepository {
    suspend fun getEventList(): Flow<NetworkResult<List<Event>>>
}

class EventRepositoryImpl(
    private val api: EventApi
) : BaseApiResponse(), EventRepository {

    override suspend fun getEventList(): Flow<NetworkResult<List<Event>>> {
        return flow {
            emit(
                safeApiCall(
                    apiCall = { api.getEventList() },
                    resultMapped = { it.map { eventResponse -> eventResponse.toModel() } }
                )
            )
        }
    }
}