package dev.vinigouveia.git_hub_challenge.features.details

import dev.vinigouveia.git_hub_challenge.data.model.UserDetailsBO

data class UserDetailsState(
    val data: UserDetailsBO? = null,
    val error: Throwable? = null,
    val isLoading: Boolean = false
)
