package dev.vinigouveia.git_hub_challenge.features.home

sealed class HomeIntent {
    object FetchUsers : HomeIntent()
    data class SearchUser(val username: String) : HomeIntent()
}
