package dev.vinigouveia.git_hub_challenge.data.home

import dev.vinigouveia.git_hub_challenge.data.model.UserBO
import dev.vinigouveia.git_hub_challenge.data.repository.MainRepository
import dev.vinigouveia.git_hub_challenge.data.usecase.HomeUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class HomeUseCaseTest {
    private lateinit var useCase: HomeUseCase

    private val repository: MainRepository = mockk()

    @BeforeEach
    fun initialize() {
        useCase = HomeUseCase(repository)
    }

    @Test
    @DisplayName("Should fetch user successfully and return an UserBO list")
    fun fetchUsersSuccessfully() = runBlocking {
        val expectedResponse = listOf(
            UserBO(username = "testName1", id = 0, avatarUrl = "avatarUrl1.com"),
            UserBO(username = "testName2", id = 1, avatarUrl = "avatarUrl2.com"),
            UserBO(username = "testName3", id = 2, avatarUrl = "avatarUrl3.com")
        )

        coEvery { repository.fetchUsers() } returns expectedResponse

        val response = useCase.fetchUsers()

        Assertions.assertEquals(expectedResponse, response)

        coVerify(exactly = 1) { repository.fetchUsers() }

        confirmVerified(repository)
    }

    @Test
    @DisplayName("Should fetch user successfully and return an empty list")
    fun searchUserSuccessfully() {
        val expectedFilteredList = listOf(
            UserBO(username = "testName1", id = 0, avatarUrl = "avatarUrl1.com")
        )

        every { repository.searchUser(any()) } returns expectedFilteredList

        val filteredList = useCase.searchUser("testName1")

        Assertions.assertEquals(expectedFilteredList, filteredList)

        verify(exactly = 1) { repository.searchUser("testName1") }

        confirmVerified(repository)
    }
}
