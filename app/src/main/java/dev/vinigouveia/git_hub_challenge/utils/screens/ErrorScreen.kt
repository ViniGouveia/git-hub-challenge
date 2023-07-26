package dev.vinigouveia.git_hub_challenge.utils.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.vinigouveia.git_hub_challenge.ui.theme.red

@Composable
fun ErrorScreen(message: Int, errorCallback: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { errorCallback() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(36.dp),
            text = stringResource(message),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.red,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )
    }
}
