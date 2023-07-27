package dev.vinigouveia.git_hub_challenge.feature.details

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import dev.vinigouveia.git_hub_challenge.R
import dev.vinigouveia.git_hub_challenge.data.model.RepositoryBO
import dev.vinigouveia.git_hub_challenge.data.model.UserDetailsBO
import dev.vinigouveia.git_hub_challenge.data.usecase.UserDetailsUseCase
import dev.vinigouveia.git_hub_challenge.features.details.UserDetailsIntent
import dev.vinigouveia.git_hub_challenge.features.details.UserDetailsState
import dev.vinigouveia.git_hub_challenge.features.details.UserDetailsViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
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
class UserDetailsViewModelTest {
    private lateinit var viewModel: UserDetailsViewModel

    private val useCase: UserDetailsUseCase = mockk()
    private val savedState: SavedStateHandle = mockk()

    @BeforeEach
    fun initialize() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = UserDetailsViewModel(useCase, savedState)
    }

    @AfterEach
    fun resetDispatcher() {
        Dispatchers.resetMain()
    }

    @Test
    @DisplayName("Should emit loading state")
    fun emitLoadingState() = runTest {
        val expectedState = UserDetailsState().copy(isLoading = true)

        viewModel.screenState.test {
            awaitItem().also {
                Assertions.assertEquals(expectedState, it)
            }
        }

    }

    @Test
    @DisplayName("Should fetch user info successfully and emit a success state with the user info")
    fun fetchUserInfoSuccessfully() = runTest {
        val expectedUserInfo = UserDetailsBO(
            name = "test",
            id = 0,
            avatarUrl = "avatarUrl.com",
            company = "company \ncompany \n",
            publicRepos = 3,
            followers = 0,
            following = 0,
            publicReposList = listOf(
                RepositoryBO(name = "test", description = "description test"),
                RepositoryBO(name = "test1", description = "description test1"),
                RepositoryBO(name = "test2", description = "description test2")
            )
        )
        val expectedState = UserDetailsState().copy(data = expectedUserInfo)

        coEvery { useCase.fetchUserInfo(any()) } returns expectedUserInfo
        every { savedState.get<String>(any()) } returns "test"

        viewModel.handleIntent(UserDetailsIntent.FetchUserInfo)

        viewModel.screenState.test {
            awaitItem().also {
                Assertions.assertEquals(expectedState, it)
            }
        }

        coVerify(exactly = 1) {
            useCase.fetchUserInfo("test")
        }

        verify(exactly = 1) {
            savedState.get<String>("username")
        }

        confirmVerified(useCase, savedState)
    }

    @Test
    @DisplayName("Should emit an error state with IOException message when fetching user info")
    fun emitIoexceptionErrorStateWhenFetchingUserInfo() = runTest {
        val error = IOException()
        val expectedState = UserDetailsState().copy(error = R.string.connection_error_message)

        coEvery { useCase.fetchUserInfo(any()) } throws error
        every { savedState.get<String>(any()) } returns "test"

        viewModel.handleIntent(UserDetailsIntent.FetchUserInfo)

        viewModel.screenState.test {
            awaitItem().also {
                Assertions.assertEquals(expectedState, it)
            }
        }

        coVerify(exactly = 1) {
            useCase.fetchUserInfo("test")
        }

        verify(exactly = 1) {
            savedState.get<String>("username")
        }

        confirmVerified(useCase)
    }

    @Test
    @DisplayName("Should emit an error state with unknown error message when fetching user info")
    fun emitUnknownErrorStateWhenFetchingUserInfo() = runTest {
        val error = Throwable()
        val expectedState = UserDetailsState().copy(error = R.string.unknown_error_message)

        coEvery { useCase.fetchUserInfo(any()) } throws error
        every { savedState.get<String>(any()) } returns "test"

        viewModel.handleIntent(UserDetailsIntent.FetchUserInfo)

        viewModel.screenState.test {
            awaitItem().also {
                Assertions.assertEquals(expectedState, it)
            }
        }

        coVerify(exactly = 1) {
            useCase.fetchUserInfo("test")
        }

        verify(exactly = 1) {
            savedState.get<String>("username")
        }

        confirmVerified(useCase)
    }

    @Test
    @DisplayName("Should search for a repository successfully and emit a success state with the result list")
    fun searchRepositorySuccessfully() = runTest {
        val expectedUser = UserDetailsBO(
            name = "test",
            id = 0,
            avatarUrl = "avatarUrl.com",
            company = "company",
            publicRepos = 0,
            followers = 0,
            following = 0,
            publicReposList = listOf(
                RepositoryBO(name = "test1", description = "description test")
            )
        )

        val expectedState = UserDetailsState().copy(data = expectedUser)

        coEvery { useCase.searchRepository(any()) } returns expectedUser

        viewModel.handleIntent(UserDetailsIntent.SearchRepository("test1"))

        viewModel.screenState.test {
            awaitItem().also {
                Assertions.assertEquals(expectedState, it)
            }
        }

        coVerify(exactly = 1) {
            useCase.searchRepository("test1")
        }

        confirmVerified(useCase)
    }
}
