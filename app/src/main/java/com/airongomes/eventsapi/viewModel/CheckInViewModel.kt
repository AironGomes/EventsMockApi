package com.airongomes.eventsapi.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airongomes.eventsapi.domain.model.CheckIn
import com.airongomes.eventsapi.domain.remote.NetworkResult
import com.airongomes.eventsapi.domain.repository.EventRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CheckInViewModel(
    private val repository: EventRepository
) : ViewModel() {

    private val _checkIntResult = MutableStateFlow<NetworkResult<Any>>(NetworkResult.Loading())
    val checkIntResult: StateFlow<NetworkResult<Any>> = _checkIntResult

    fun checkIn(checkIn: CheckIn) {
        viewModelScope.launch {
            repository.checkIn(checkIn)
                .catch {
                    _checkIntResult.value = NetworkResult.Error()
                }
                .collect {
                    _checkIntResult.value = it
                }
        }
    }
}