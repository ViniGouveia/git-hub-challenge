package dev.vinigouveia.git_hub_challenge.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import dev.vinigouveia.git_hub_challenge.ui.theme.lightGray

@Composable
fun CustomDivider(modifier: Modifier = Modifier) {
    Spacer(
        modifier = modifier
            .padding(
                horizontal = 20.dp,
                vertical = 12.dp
            )
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.lightGray,
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.lightGray
                    )
                )
            )
            .fillMaxWidth()
            .height(1.dp)
    )
}

