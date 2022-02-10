package com.airongomes.eventsapi.viewModel

import androidx.lifecycle.ViewModel
import com.airongomes.eventsapi.domain.repository.EventRepository

class HomeViewModel(
    private val repository: EventRepository,
): ViewModel() {
    
    suspend fun fetchEventList() = repository.getEventList()
}