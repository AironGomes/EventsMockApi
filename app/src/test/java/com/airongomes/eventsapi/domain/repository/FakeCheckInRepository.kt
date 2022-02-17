package com.airongomes.eventsapi.domain.repository

import com.airongomes.eventsapi.domain.model.CheckIn
import com.airongomes.eventsapi.domain.remote.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakeCheckInRepository : CheckInRepository {
    var shouldReturnError: Boolean = false

    override suspend fun checkIn(checkIn: CheckIn): Flow<NetworkResult<Any>> =
        if (shouldReturnError) flow { emit(NetworkResult.Error()) }
        else flow {
            emit(
                NetworkResult.Success(data = Any())
            )
        }

}