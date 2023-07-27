package dev.vinigouveia.git_hub_challenge.features.home

import androidx.annotation.StringRes
import dev.vinigouveia.git_hub_challenge.data.model.UserBO

data class HomeState(
    val isLoading: Boolean = false,
    val data: List<UserBO> = listOf(),
    @StringRes val error: Int = 0
)
