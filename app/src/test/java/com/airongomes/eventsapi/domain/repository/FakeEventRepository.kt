package com.airongomes.eventsapi.domain.repository

import com.airongomes.eventsapi.domain.model.CheckIn
import com.airongomes.eventsapi.domain.model.Event
import com.airongomes.eventsapi.domain.remote.NetworkResult
import com.airongomes.eventsapi.fakeEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakeEventRepository : EventRepository {
    var shouldReturnError: Boolean = false

    override suspend fun getEventList(): Flow<NetworkResult<List<Event>>> =
        if (shouldReturnError) flow { emit(NetworkResult.Error()) }
        else flow {
            emit(
                NetworkResult.Success(
                    data = listOf(fakeEvent)
                )
            )
        }

    override suspend fun checkIn(checkIn: CheckIn): Flow<NetworkResult<Any>> =
        if (shouldReturnError) flow { emit(NetworkResult.Error()) }
        else flow {
            emit(
                NetworkResult.Success(data = Any())
            )
        }

}