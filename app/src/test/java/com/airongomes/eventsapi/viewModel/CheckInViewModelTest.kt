package com.airongomes.eventsapi.viewModel

import com.airongomes.eventsapi.MainCoroutineRule
import com.airongomes.eventsapi.domain.remote.NetworkResult
import com.airongomes.eventsapi.domain.repository.FakeEventRepository
import com.airongomes.eventsapi.domain.usecase.CheckInUseCase
import com.airongomes.eventsapi.domain.usecase.CheckInUseCaseImpl
import com.airongomes.eventsapi.fakeCheckIn
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
class CheckInViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var repository: FakeEventRepository
    private lateinit var checkInUseCase: CheckInUseCase
    private lateinit var viewModel: CheckInViewModel

    @Before
    fun setup() {
        repository = FakeEventRepository()
        checkInUseCase = CheckInUseCaseImpl(repository)
        viewModel = CheckInViewModel(checkInUseCase)
        viewModel.checkIn(fakeCheckIn)
    }

    @Test
    fun `Given flow with NetworkError Then add element NetworkError to stateFlow`() =
        runTest {
            repository.shouldReturnError = true
            val checkInResult = viewModel.checkInResult.drop(1).first()
            assertThat(checkInResult, instanceOf(NetworkResult.Error<Any>()::class.java))
        }

    @Test
    fun `Given flow with NetworkSuccess Then add element NetworkSuccess to stateFlow`() =
        runTest {
            val checkInResult = viewModel.checkInResult.drop(1).first()
            assertThat(checkInResult, instanceOf(NetworkResult.Success(anything())::class.java))
        }
}

