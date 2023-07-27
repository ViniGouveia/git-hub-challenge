package dev.vinigouveia.git_hub_challenge.data.model.response

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    val name: String?,
    val company: String?,
    @SerializedName("public_repos")
    val publicRepos: Int,
    val followers: Int,
    val following: Int
)
