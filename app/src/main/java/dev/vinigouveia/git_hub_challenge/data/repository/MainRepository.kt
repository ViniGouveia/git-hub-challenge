package dev.vinigouveia.git_hub_challenge.data.repository

import dev.vinigouveia.git_hub_challenge.data.model.RepositoryBO
import dev.vinigouveia.git_hub_challenge.data.model.UserBO
import dev.vinigouveia.git_hub_challenge.data.model.UserDetailsBO
import dev.vinigouveia.git_hub_challenge.data.remote.ServiceApi
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val serviceApi: ServiceApi
) {
    private var cachedUsersList = listOf<UserBO>()
    private var cachedUser = UserDetailsBO()

    suspend fun fetchUsers(): List<UserBO> {
        val response = serviceApi.fetchUsers()
        cachedUsersList = response.body().orEmpty().map(::UserBO)
        return cachedUsersList
    }

    fun searchUser(username: String) = cachedUsersList.filter { it.username.contains(username) }

    suspend fun fetchUserInfo(username: String): UserDetailsBO {
        val userResponse = serviceApi.fetchUserInfo(username)

        val repositoriesResponse =
            serviceApi.fetchUserRepositories(username)

        cachedUser = UserDetailsBO(
            userResponse.body(),
            repositoriesResponse.body()?.map { RepositoryBO(it) }.orEmpty()
        )

        return cachedUser
    }

    fun searchRepository(searchParam: String): UserDetailsBO {
        val filteredList = cachedUser.publicReposList.filter { it.name.contains(searchParam) }
        return cachedUser.copy(publicReposList = filteredList)
    }
}
