package com.airongomes.eventsapi.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airongomes.eventsapi.domain.model.CheckIn
import com.airongomes.eventsapi.domain.remote.NetworkResult
import com.airongomes.eventsapi.domain.usecase.CheckInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CheckInViewModel(
    private val checkInUseCase: CheckInUseCase
) : ViewModel() {

    private val _checkInResult = MutableStateFlow<NetworkResult<Any>>(NetworkResult.Loading())
    val checkInResult: StateFlow<NetworkResult<Any>> = _checkInResult

    fun checkIn(checkIn: CheckIn) {
        viewModelScope.launch {
            checkInUseCase(checkIn)
                .collect {
                    _checkInResult.value = it
                }
        }
    }
}