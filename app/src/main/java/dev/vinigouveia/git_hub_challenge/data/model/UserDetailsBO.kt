package dev.vinigouveia.git_hub_challenge.data.model

import dev.vinigouveia.git_hub_challenge.data.model.response.UserInfoResponse

data class UserDetailsBO(
    val username: String = "",
    val id: Int = 0,
    val avatarUrl: String = "",
    val profileUrl: String = "",
    val name: String = "",
    val company: String = "",
    val blog: String = "",
    val location: String = "",
    val email: String = "",
    val bio: String = "",
    val twitterUsername: String = "",
    val publicRepos: Int = 0,
    val followers: Int = 0,
    val following: Int = 0,
    val publicReposList: List<RepositoryBO> = listOf()
) {
    constructor(response: UserInfoResponse?, repositories: List<RepositoryBO>) : this(
        username = response?.login ?: "",
        id = response?.id ?: 0,
        avatarUrl = response?.avatarUrl ?: "",
        profileUrl = response?.profileUrl ?: "",
        name = response?.name ?: "",
        company = response?.company ?: "",
        blog = response?.blog ?: "",
        location = response?.location ?: "",
        email = response?.email ?: "",
        bio = response?.bio ?: "",
        twitterUsername = response?.twitterUsername ?: "",
        publicRepos = response?.publicRepos ?: 0,
        followers = response?.followers ?: 0,
        following = response?.following ?: 0,
        publicReposList = repositories
    )
}
