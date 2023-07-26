package dev.vinigouveia.git_hub_challenge.features.home

import dev.vinigouveia.git_hub_challenge.data.model.UserBO

data class HomeState(
    val isLoading: Boolean = false,
    val data: List<UserBO> = listOf(),
    val error: Throwable? = null
)
