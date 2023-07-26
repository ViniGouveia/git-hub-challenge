package dev.vinigouveia.git_hub_challenge.data.usecase

import dev.vinigouveia.git_hub_challenge.data.repository.MainRepository
import javax.inject.Inject

class UserDetailsUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend fun fetchUserInfo(username: String) = repository.fetchUserInfo(username)

    fun searchRepository(searchParam: String) = repository.searchRepository(searchParam)
}
