package com.airongomes.eventsapi.viewModel

import androidx.lifecycle.ViewModel
import com.airongomes.eventsapi.domain.usecase.EventUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class HomeViewModel(
    val useCase: EventUseCase,
    val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    suspend fun fetchEventList() = useCase.getEventList()
}