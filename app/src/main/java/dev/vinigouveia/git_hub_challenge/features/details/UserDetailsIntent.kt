package dev.vinigouveia.git_hub_challenge.features.details

sealed class UserDetailsIntent {
    object FetchUserInfo : UserDetailsIntent()
    data class SearchRepository(val searchParam: String) : UserDetailsIntent()
}
