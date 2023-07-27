package dev.vinigouveia.git_hub_challenge.features.details

import androidx.annotation.StringRes
import dev.vinigouveia.git_hub_challenge.data.model.UserDetailsBO

data class UserDetailsState(
    val data: UserDetailsBO? = null,
    @StringRes val error: Int = 0,
    val isLoading: Boolean = false
)
