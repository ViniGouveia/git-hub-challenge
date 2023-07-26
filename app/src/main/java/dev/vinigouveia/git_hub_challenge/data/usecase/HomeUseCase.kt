package dev.vinigouveia.git_hub_challenge.data.usecase

import dev.vinigouveia.git_hub_challenge.data.repository.MainRepository
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend fun fetchUsers() = repository.fetchUsers()

    fun searchUser(username: String) = repository.searchUser(username)
}
