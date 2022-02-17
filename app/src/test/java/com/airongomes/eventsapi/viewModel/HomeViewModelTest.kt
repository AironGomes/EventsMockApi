package com.airongomes.eventsapi.viewModel

import com.airongomes.eventsapi.MainCoroutineRule
import com.airongomes.eventsapi.domain.remote.NetworkResult
import com.airongomes.eventsapi.domain.repository.FakeEventRepository
import com.airongomes.eventsapi.domain.usecase.FetchEventsUseCase
import com.airongomes.eventsapi.domain.usecase.FetchEventsUseCaseImpl
import com.airongomes.eventsapi.fakeEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var repository: FakeEventRepository
    private lateinit var fetchEventsUseCase: FetchEventsUseCase
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        repository = FakeEventRepository()
        fetchEventsUseCase = FetchEventsUseCaseImpl(repository)
        viewModel = HomeViewModel(fetchEventsUseCase)
    }

    @Test
    fun `Given flow with NetworkError Then add element NetworkError to stateFlow`() =
        runTest {
            repository.shouldReturnError = true
            val eventList = viewModel.eventList.drop(1).first()
            assertThat(eventList, instanceOf(NetworkResult.Error<Any>()::class.java))
        }

    @Test
    fun `Given flow with NetworkSuccess Then add element NetworkSuccess with list of events to stateFlow`() =
        runTest {
            val eventList = viewModel.eventList.drop(1).first()
            assertThat(eventList, instanceOf(NetworkResult.Success(anything())::class.java))
            assertThat(eventList.data, hasItem(fakeEvent))

        }
}

