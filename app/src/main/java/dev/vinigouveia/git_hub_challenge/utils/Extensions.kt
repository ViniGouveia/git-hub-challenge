package dev.vinigouveia.git_hub_challenge.utils

import dev.vinigouveia.git_hub_challenge.R
import java.io.IOException

fun Throwable?.getErrorMessage(): Int {
    return when (this) {
        is IOException -> R.string.connection_error_message
        else -> R.string.unknown_error_message
    }
}
