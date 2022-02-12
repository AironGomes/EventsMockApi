package com.airongomes.eventsapi.di

import com.airongomes.eventsapi.domain.remote.api.EventApi
import com.airongomes.eventsapi.domain.remote.instantiateApi
import com.airongomes.eventsapi.domain.repository.EventRepository
import com.airongomes.eventsapi.viewModel.CheckInViewModel
import com.airongomes.eventsapi.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val eventModule = module {
    single { instantiateApi(EventApi::class.java) }
    single { EventRepository(get()) }

    viewModel { HomeViewModel(get()) }
    viewModel { CheckInViewModel(get()) }
}