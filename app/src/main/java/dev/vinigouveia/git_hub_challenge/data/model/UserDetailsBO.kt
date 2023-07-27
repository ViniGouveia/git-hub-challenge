package dev.vinigouveia.git_hub_challenge.data.model

import dev.vinigouveia.git_hub_challenge.data.model.response.UserInfoResponse
import dev.vinigouveia.git_hub_challenge.utils.formatCompany

data class UserDetailsBO(
    val id: Int = 0,
    val avatarUrl: String = "",
    val name: String = "",
    val company: String = "",
    val publicRepos: Int = 0,
    val followers: Int = 0,
    val following: Int = 0,
    val publicReposList: List<RepositoryBO> = listOf()
) {
    constructor(response: UserInfoResponse?, repositories: List<RepositoryBO>) : this(
        id = response?.id ?: 0,
        avatarUrl = response?.avatarUrl ?: "",
        name = response?.name ?: "",
        company = response?.company?.formatCompany() ?: "",
        publicRepos = response?.publicRepos ?: 0,
        followers = response?.followers ?: 0,
        following = response?.following ?: 0,
        publicReposList = repositories
    )
}
