package dev.vinigouveia.git_hub_challenge.data.remote

import dev.vinigouveia.git_hub_challenge.data.model.response.RepositoryResponse
import dev.vinigouveia.git_hub_challenge.data.model.response.UserInfoResponse
import dev.vinigouveia.git_hub_challenge.data.model.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApi {
    @GET("users?per_page=100")
    suspend fun fetchUsers(): List<UserResponse>

    @GET("users/{username}")
    suspend fun fetchUserInfo(@Path("username") username: String): UserInfoResponse

    @GET("users/{username}/repos?per_page=100")
    suspend fun fetchUserRepositories(@Path("username") username: String): List<RepositoryResponse>
}
