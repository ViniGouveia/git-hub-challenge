package dev.vinigouveia.git_hub_challenge.data.remote

import dev.vinigouveia.git_hub_challenge.data.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {
    @GET("users?per_page=100")
    suspend fun fetchUsers(): Response<List<UserResponse>>
}
