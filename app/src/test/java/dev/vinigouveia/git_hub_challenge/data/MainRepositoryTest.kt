package dev.vinigouveia.git_hub_challenge.data

import dev.vinigouveia.git_hub_challenge.data.model.RepositoryBO
import dev.vinigouveia.git_hub_challenge.data.model.UserBO
import dev.vinigouveia.git_hub_challenge.data.model.UserDetailsBO
import dev.vinigouveia.git_hub_challenge.data.model.response.RepositoryResponse
import dev.vinigouveia.git_hub_challenge.data.model.response.UserInfoResponse
import dev.vinigouveia.git_hub_challenge.data.model.response.UserResponse
import dev.vinigouveia.git_hub_challenge.data.remote.ServiceApi
import dev.vinigouveia.git_hub_challenge.data.repository.MainRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MainRepositoryTest {

    private lateinit var repository: MainRepository

    private val serviceApiMock: ServiceApi = mockk()

    @BeforeEach
    fun initialize() {
        repository = MainRepository(serviceApiMock)
    }

    @Test
    @DisplayName("Should fetch users successfully and return a mapped UserBO list")
    fun fetchUsersSuccessfully() = runBlocking {
        val expectedResponse = listOf(
            UserBO(username = "testName1", id = 0, avatarUrl = "avatarUrl1.com"),
            UserBO(username = "testName2", id = 1, avatarUrl = "avatarUrl2.com"),
            UserBO(username = "testName3", id = 2, avatarUrl = "avatarUrl3.com")
        )
        val serviceResponse = listOf(
            UserResponse(login = "testName1", id = 0, avatarUrl = "avatarUrl1.com"),
            UserResponse(login = "testName2", id = 1, avatarUrl = "avatarUrl2.com"),
            UserResponse(login = "testName3", id = 2, avatarUrl = "avatarUrl3.com")
        )

        coEvery { serviceApiMock.fetchUsers() } returns serviceResponse

        val response = repository.fetchUsers()

        Assertions.assertEquals(expectedResponse, response)

        coVerify(exactly = 1) { serviceApiMock.fetchUsers() }

        confirmVerified(serviceApiMock)
    }

    @Test
    @DisplayName("Should search user successfully and return a filtered list")
    fun searchUserSuccessfully() {
        fetchUsersSuccessfully()

        val expectedFilteredList = listOf(
            UserBO(username = "testName1", id = 0, avatarUrl = "avatarUrl1.com")
        )

        val filteredList = repository.searchUser("testName1")

        Assertions.assertEquals(expectedFilteredList, filteredList)
    }

    @Test
    @DisplayName("Should fetch user info and repositories successfully and return a mapped UserDetailsBO")
    fun fetchUserInfoSuccessfully() = runBlocking {
        val expectedResponse = UserDetailsBO(
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
        val userResponse = UserInfoResponse(
            name = "test",
            id = 0,
            avatarUrl = "avatarUrl.com",
            company = "company, company",
            publicRepos = 3,
            followers = 0,
            following = 0
        )
        val repositoryResponse = listOf(
            RepositoryResponse(name = "test", description = "description test"),
            RepositoryResponse(name = "test1", description = "description test1"),
            RepositoryResponse(name = "test2", description = "description test2")
        )

        coEvery { serviceApiMock.fetchUserInfo(any()) } returns userResponse
        coEvery { serviceApiMock.fetchUserRepositories(any()) } returns repositoryResponse

        val response = repository.fetchUserInfo("test")

        Assertions.assertEquals(expectedResponse, response)

        coVerify(exactly = 1) {
            serviceApiMock.fetchUserInfo("test")
            serviceApiMock.fetchUserRepositories("test")
        }

        confirmVerified(serviceApiMock)
    }

    @Test
    @DisplayName("Should search a repository successfully and return a UserDetailsBO with a filtered repository list")
    fun searchRepositorySuccessfully() {
        fetchUserInfoSuccessfully()

        val expectedUserWithFilteredList = UserDetailsBO(
            name = "test",
            id = 0,
            avatarUrl = "avatarUrl.com",
            company = "company \ncompany \n",
            publicRepos = 3,
            followers = 0,
            following = 0,
            publicReposList = listOf(
                RepositoryBO(name = "test1", description = "description test1")
            )
        )

        val filteredUser = repository.searchRepository("test1")

        Assertions.assertEquals(expectedUserWithFilteredList, filteredUser)
    }
}
