package com.airongomes.eventsapi.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airongomes.eventsapi.domain.model.Event
import com.airongomes.eventsapi.domain.remote.NetworkResult
import com.airongomes.eventsapi.domain.repository.EventRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: EventRepository,
): ViewModel() {

    init {
        fetchEventList()
    }

    private val _eventList = MutableStateFlow<NetworkResult<List<Event>>>(NetworkResult.Loading())
    val eventList: StateFlow<NetworkResult<List<Event>>> = _eventList

    fun fetchEventList() {
        viewModelScope.launch {
            repository.getEventList()
                .catch {
                    _eventList.value = NetworkResult.Error("Unable to load data.")
                }
                .collect {
                    _eventList.value = it
                }
        }
    }
}