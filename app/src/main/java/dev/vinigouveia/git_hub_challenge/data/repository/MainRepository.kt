package dev.vinigouveia.git_hub_challenge.data.repository

import dev.vinigouveia.git_hub_challenge.data.model.UserBO
import dev.vinigouveia.git_hub_challenge.data.remote.ServiceApi
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val serviceApi: ServiceApi
) {
    private var cachedUsersList = listOf<UserBO>()

    suspend fun fetchUsers(): List<UserBO> {
        val response = serviceApi.fetchUsers()
        cachedUsersList = response.body().orEmpty().map(::UserBO)
        return cachedUsersList
    }

    fun searchUser(username: String) = cachedUsersList.filter { it.username.contains(username) }
}
