package dev.vinigouveia.git_hub_challenge.features.details.composables

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import dev.vinigouveia.git_hub_challenge.data.model.UserDetailsBO
import dev.vinigouveia.git_hub_challenge.ui.theme.lightGray
import dev.vinigouveia.git_hub_challenge.ui.theme.white
import dev.vinigouveia.git_hub_challenge.utils.components.LoadImage
import kotlin.math.min

@Composable
fun UserInfoCard(userDetails: UserDetailsBO, scrollState: LazyListState) {
    val offset = min(
        1f,
        1 - (scrollState.firstVisibleItemScrollOffset / 600f + scrollState.firstVisibleItemIndex)
    )
    val size by animateDpAsState(targetValue = max(100.dp, 140.dp * offset))

    Surface(
        shadowElevation = 4.dp,
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LoadImage(imageUrl = userDetails.avatarUrl, size = size)
                Column(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = userDetails.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = MaterialTheme.colorScheme.white
                    )
                    Text(
                        text = userDetails.company,
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Normal),
                        color = MaterialTheme.colorScheme.lightGray.copy(alpha = .7f)
                    )
                }
            } // Row

            Row(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.secondary)
                    .padding(horizontal = 12.dp, vertical = 2.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${userDetails.following} Following",
                    color = MaterialTheme.colorScheme.white,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "${userDetails.followers} Followers",
                    color = MaterialTheme.colorScheme.white,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "${userDetails.publicRepos} Public Repos",
                    color = MaterialTheme.colorScheme.white,
                    style = MaterialTheme.typography.labelSmall
                )
            } // Row
        } // Column
    } // Surface
}
