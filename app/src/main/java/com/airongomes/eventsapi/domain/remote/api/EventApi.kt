package com.airongomes.eventsapi.domain.remote.api

import com.airongomes.eventsapi.domain.remote.request.CheckInRequest
import com.airongomes.eventsapi.domain.remote.response.EventResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EventApi {

    @GET("/api/events")
    suspend fun getEventList(): Response<List<EventResponse>>

    @POST("/api/checkin")
    suspend fun checkIn(@Body checkIn: CheckInRequest): Response<Any>
}