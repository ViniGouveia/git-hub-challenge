package dev.vinigouveia.git_hub_challenge.data.model.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val login: String?,
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String?
)
