package com.airongomes.eventsapi.di

import com.airongomes.eventsapi.domain.remote.api.EventApi
import com.airongomes.eventsapi.domain.remote.instantiateApi
import com.airongomes.eventsapi.domain.repository.CheckInRepository
import com.airongomes.eventsapi.domain.repository.CheckInRepositoryImpl
import com.airongomes.eventsapi.domain.repository.EventRepository
import com.airongomes.eventsapi.domain.repository.EventRepositoryImpl
import com.airongomes.eventsapi.domain.usecase.CheckInUseCase
import com.airongomes.eventsapi.domain.usecase.CheckInUseCaseImpl
import com.airongomes.eventsapi.domain.usecase.FetchEventsUseCase
import com.airongomes.eventsapi.domain.usecase.FetchEventsUseCaseImpl
import com.airongomes.eventsapi.viewModel.CheckInViewModel
import com.airongomes.eventsapi.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val eventModule = module {
    single { instantiateApi(EventApi::class.java) }
    single<EventRepository> { EventRepositoryImpl(get()) }
    single<CheckInRepository> { CheckInRepositoryImpl(get()) }
    factory<FetchEventsUseCase> { FetchEventsUseCaseImpl(get()) }
    factory<CheckInUseCase> { CheckInUseCaseImpl(get()) }

    viewModel { HomeViewModel(get()) }
    viewModel { CheckInViewModel(get()) }
}