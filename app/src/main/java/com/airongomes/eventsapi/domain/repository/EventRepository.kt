package com.airongomes.eventsapi.domain.repository

import com.airongomes.eventsapi.domain.model.CheckIn
import com.airongomes.eventsapi.domain.model.Event
import com.airongomes.eventsapi.domain.remote.BaseApiResponse
import com.airongomes.eventsapi.domain.remote.NetworkResult
import com.airongomes.eventsapi.domain.remote.api.EventApi
import com.airongomes.eventsapi.domain.remote.request.toRequest
import com.airongomes.eventsapi.domain.remote.response.toModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EventRepository(
    private val api: EventApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseApiResponse() {

    suspend fun getEventList(): Flow<NetworkResult<List<Event>>> {
        return flow {
            emit(
                safeApiCall(
                    apiCall = { api.getEventList() },
                    resultMapped = { it.map { eventResponse -> eventResponse.toModel() } }
                )
            )
        }.flowOn(dispatcher)
    }

    suspend fun checkIn(checkIn: CheckIn): Flow<NetworkResult<Any>> {
        return flow {
            emit(
                safeApiCall(
                    apiCall = { api.checkIn(checkIn.toRequest()) },
                    resultMapped = {it}
                )
            )
        }.flowOn(dispatcher)
    }
}