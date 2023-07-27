package dev.vinigouveia.git_hub_challenge.feature.home

import app.cash.turbine.test
import dev.vinigouveia.git_hub_challenge.R
import dev.vinigouveia.git_hub_challenge.data.model.UserBO
import dev.vinigouveia.git_hub_challenge.data.usecase.HomeUseCase
import dev.vinigouveia.git_hub_challenge.features.home.HomeIntent
import dev.vinigouveia.git_hub_challenge.features.home.HomeState
import dev.vinigouveia.git_hub_challenge.features.home.HomeViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    private val useCase: HomeUseCase = mockk()

    @BeforeEach
    fun initialize() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = HomeViewModel(useCase)
    }

    @AfterEach
    fun resetDispatcher() {
        Dispatchers.resetMain()
    }

    @Test
    @DisplayName("Should emit loading state")
    fun emitLoadingState() = runTest {
        val expectedState = HomeState().copy(isLoading = true)

        viewModel.screenState.test {
            awaitItem().also {
                Assertions.assertEquals(expectedState, it)
            }
        }

    }

    @Test
    @DisplayName("Should fetch users list successfully and emit a success state with the result list")
    fun fetchUsersSuccessfully() = runTest {
        val expectedList = listOf(
            UserBO(username = "testName1", id = 0, avatarUrl = "avatarUrl1.com"),
            UserBO(username = "testName2", id = 1, avatarUrl = "avatarUrl2.com"),
            UserBO(username = "testName3", id = 2, avatarUrl = "avatarUrl3.com")
        )
        val expectedState = HomeState().copy(data = expectedList)

        coEvery { useCase.fetchUsers() } returns expectedList

        viewModel.handleIntent(HomeIntent.FetchUsers)

        viewModel.screenState.test {
            awaitItem().also {
                Assertions.assertEquals(expectedState, it)
            }
        }

        coVerify(exactly = 1) {
            useCase.fetchUsers()
        }

        confirmVerified(useCase)
    }

    @Test
    @DisplayName("Should fetch users list successfully and emit a success state with an empty list")
    fun fetchUsersSuccessfullyWithEmptyList() = runTest {
        val expectedList = listOf<UserBO>()
        val expectedState = HomeState().copy(data = expectedList)

        coEvery { useCase.fetchUsers() } returns listOf()

        viewModel.handleIntent(HomeIntent.FetchUsers)

        viewModel.screenState.test {
            awaitItem().also {
                Assertions.assertEquals(expectedState, it)
            }
        }

        coVerify(exactly = 1) {
            useCase.fetchUsers()
        }

        confirmVerified(useCase)
    }

    @Test
    @DisplayName("Should emit an error state with IOException message when fetching Users List")
    fun emitIoexceptionErrorStateWhenFetchingUsers() = runTest {
        val error = IOException()
        val expectedState = HomeState().copy(error = R.string.connection_error_message)

        coEvery { useCase.fetchUsers() } throws error

        viewModel.handleIntent(HomeIntent.FetchUsers)

        viewModel.screenState.test {
            awaitItem().also {
                Assertions.assertEquals(expectedState, it)
            }
        }

        coVerify(exactly = 1) {
            useCase.fetchUsers()
        }

        confirmVerified(useCase)
    }

    @Test
    @DisplayName("Should emit an error state with unknown error message when fetching Users List")
    fun emitUnknownErrorStateWhenFetchingUsers() = runTest {
        val error = Throwable()
        val expectedState = HomeState().copy(error = R.string.unknown_error_message)

        coEvery { useCase.fetchUsers() } throws error

        viewModel.handleIntent(HomeIntent.FetchUsers)

        viewModel.screenState.test {
            awaitItem().also {
                Assertions.assertEquals(expectedState, it)
            }
        }

        coVerify(exactly = 1) {
            useCase.fetchUsers()
        }

        confirmVerified(useCase)
    }

    @Test
    @DisplayName("Should search for an users successfully and emit a success state with the result list")
    fun searchUserSuccessfully() = runTest {
        val expectedList = listOf(
            UserBO(username = "testName1", id = 0, avatarUrl = "avatarUrl1.com")
        )
        val expectedState = HomeState().copy(data = expectedList)

        coEvery { useCase.searchUser(any()) } returns expectedList

        viewModel.handleIntent(HomeIntent.SearchUser("testName1"))

        viewModel.screenState.test {
            awaitItem().also {
                Assertions.assertEquals(expectedState, it)
            }
        }

        coVerify(exactly = 1) {
            useCase.searchUser("testName1")
        }

        confirmVerified(useCase)
    }

}
