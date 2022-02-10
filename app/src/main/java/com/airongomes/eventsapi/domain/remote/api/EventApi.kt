package com.airongomes.eventsapi.domain.remote.api

import com.airongomes.eventsapi.domain.remote.response.EventResponse
import retrofit2.Response
import retrofit2.http.GET

interface EventApi {

    @GET("/api/events")
    suspend fun getEventList(): Response<List<EventResponse>>
}