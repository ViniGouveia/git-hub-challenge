package dev.vinigouveia.git_hub_challenge.data.details

import dev.vinigouveia.git_hub_challenge.data.model.RepositoryBO
import dev.vinigouveia.git_hub_challenge.data.model.UserDetailsBO
import dev.vinigouveia.git_hub_challenge.data.repository.MainRepository
import dev.vinigouveia.git_hub_challenge.data.usecase.UserDetailsUseCase
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

class UserDetailsUseCaseTest {
    private lateinit var useCase: UserDetailsUseCase

    private val repository: MainRepository = mockk()

    @BeforeEach
    fun initialize() {
        useCase = UserDetailsUseCase(repository)
    }

    @Test
    @DisplayName("Should fetch user info successfully and return an UserDetailsBO")
    fun fetchUserInfoSuccessfully() = runBlocking {
        val expectedResponse = UserDetailsBO(
            name = "test",
            id = 0,
            avatarUrl = "avatarUrl.com",
            company = "company",
            publicRepos = 0,
            followers = 0,
            following = 0,
            publicReposList = listOf()
        )

        coEvery { repository.fetchUserInfo(any()) } returns expectedResponse

        val response = useCase.fetchUserInfo("test")

        Assertions.assertEquals(expectedResponse, response)

        coVerify(exactly = 1) { repository.fetchUserInfo("test") }

        confirmVerified(repository)
    }

    @Test
    @DisplayName("Should search an repository successfully and return an user with filtered repository list")
    fun searchRepositorySuccessfully() {
        val expectedResponse = UserDetailsBO(
            name = "test",
            id = 0,
            avatarUrl = "avatarUrl.com",
            company = "company",
            publicRepos = 0,
            followers = 0,
            following = 0,
            publicReposList = listOf(
                RepositoryBO(name = "test", description = "description test")
            )
        )

        every { repository.searchRepository(any()) } returns expectedResponse

        val filteredList = useCase.searchRepository("test")

        Assertions.assertEquals(expectedResponse, filteredList)

        verify(exactly = 1) { repository.searchRepository("test") }

        confirmVerified(repository)
    }
}
