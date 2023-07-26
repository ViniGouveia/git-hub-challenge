package dev.vinigouveia.git_hub_challenge.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.vinigouveia.git_hub_challenge.R
import dev.vinigouveia.git_hub_challenge.ui.theme.white

@Composable
fun LoadImage(imageUrl: String, size: Dp) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .error(R.drawable.github_logo_black)
            .placeholder(R.drawable.github_logo_black)
            .build(),
        contentDescription = "User image",
        modifier = Modifier
            .padding(8.dp)
            .size(size)
            .clip(CircleShape)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.white,
                shape = CircleShape
            )
            .background(MaterialTheme.colorScheme.white),
        contentScale = ContentScale.Crop
    )
}
