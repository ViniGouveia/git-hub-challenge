package dev.vinigouveia.git_hub_challenge.data.model

import dev.vinigouveia.git_hub_challenge.data.model.response.RepositoryResponse

data class RepositoryBO(
    val name: String = "",
    val fullName: String = "",
    val description: String = ""
) {
    constructor(response: RepositoryResponse?) : this(
        name = response?.name ?: "",
        fullName = response?.fullName ?: "",
        description = response?.description ?: ""
    )
}
