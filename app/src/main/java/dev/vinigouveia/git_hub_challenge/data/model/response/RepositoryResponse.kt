package dev.vinigouveia.git_hub_challenge.data.model.response

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    val name: String?,
    @SerializedName("fullname")
    val fullName: String?,
    val description: String?
)
