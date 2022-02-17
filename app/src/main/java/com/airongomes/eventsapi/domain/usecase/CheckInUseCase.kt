package com.airongomes.eventsapi.domain.usecase

import com.airongomes.eventsapi.domain.model.CheckIn
import com.airongomes.eventsapi.domain.remote.NetworkResult
import com.airongomes.eventsapi.domain.repository.CheckInRepository
import kotlinx.coroutines.flow.Flow

interface CheckInUseCase {
    suspend operator fun invoke(checkIn: CheckIn): Flow<NetworkResult<Any>>
}

class CheckInUseCaseImpl(
    private val repository: CheckInRepository
) : CheckInUseCase {

    override suspend fun invoke(checkIn: CheckIn) = repository.checkIn(checkIn)

}