package dev.vinigouveia.git_hub_challenge.data.repository

import dev.vinigouveia.git_hub_challenge.data.remote.ServiceApi
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val serviceApi: ServiceApi
) {
}
