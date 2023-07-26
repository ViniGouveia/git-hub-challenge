package dev.vinigouveia.git_hub_challenge.data.model.response

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    val login: String?,
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    val profileUrl: String?,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val bio: String?,
    @SerializedName("twitter_username")
    val twitterUsername: String?,
    @SerializedName("public_repos")
    val publicRepos: Int,
    val followers: Int,
    val following: Int
)
