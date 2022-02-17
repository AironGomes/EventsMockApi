package com.airongomes.eventsapi.domain.repository

import com.airongomes.eventsapi.domain.model.CheckIn
import com.airongomes.eventsapi.domain.remote.BaseApiResponse
import com.airongomes.eventsapi.domain.remote.NetworkResult
import com.airongomes.eventsapi.domain.remote.api.EventApi
import com.airongomes.eventsapi.domain.remote.request.toRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


interface CheckInRepository {
    suspend fun checkIn(checkIn: CheckIn): Flow<NetworkResult<Any>>
}

class CheckInRepositoryImpl(
    private val api: EventApi
) : BaseApiResponse(), CheckInRepository {

    override suspend fun checkIn(checkIn: CheckIn): Flow<NetworkResult<Any>> {
        return flow {
            emit(
                safeApiCall(
                    apiCall = { api.checkIn(checkIn.toRequest()) },
                    resultMapped = {it}
                )
            )
        }
    }
}