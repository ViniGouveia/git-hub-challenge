package dev.vinigouveia.git_hub_challenge.utils.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import dev.vinigouveia.git_hub_challenge.ui.theme.lightGray

@Composable
fun LoadingScreen() {
    val loadingColors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.lightGray,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primary
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val transition = rememberInfiniteTransition()
        val rotateAnimation by transition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 540, easing = LinearEasing)
            )
        )

        CircularProgressIndicator(
            modifier = Modifier
                .size(100.dp)
                .rotate(rotateAnimation)
                .border(
                    width = 4.dp,
                    brush = Brush.sweepGradient(loadingColors),
                    shape = CircleShape
                ),
            strokeWidth = 2.dp,
            color = MaterialTheme.colorScheme.lightGray,
            progress = 1f,
        )
    }
}
