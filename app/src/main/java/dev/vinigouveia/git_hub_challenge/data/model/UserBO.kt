package dev.vinigouveia.git_hub_challenge.data.model

import dev.vinigouveia.git_hub_challenge.data.model.response.UserResponse

data class UserBO(
    val username: String,
    val id: Int,
    val avatarUrl: String
) {
    constructor(userResponse: UserResponse?) : this(
        username = userResponse?.login ?: "",
        id = userResponse?.id ?: 0,
        avatarUrl = userResponse?.avatarUrl ?: ""
    )
}
