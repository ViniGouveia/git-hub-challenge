package dev.vinigouveia.git_hub_challenge.features.details.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.vinigouveia.git_hub_challenge.R
import dev.vinigouveia.git_hub_challenge.data.model.RepositoryBO
import dev.vinigouveia.git_hub_challenge.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRepositoryItem(data: RepositoryBO) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    val expandTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeIn(animationSpec = tween(300))
    }

    val collapseTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeOut(
            animationSpec = tween(300)
        )
    }

    Card(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        onClick = { isExpanded = !isExpanded }
    ) {
        RepositoryHeader(isExpanded = isExpanded, repository = data)
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandTransition,
            exit = collapseTransition
        ) {
            RepositoryDescription(description = data.description)
        }
    }
}

@Composable
fun RepositoryHeader(isExpanded: Boolean, repository: RepositoryBO) {
    val rotation by animateFloatAsState(targetValue = if (isExpanded) 90f else 0f)

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.repository_icon),
            contentDescription = "Repository icon",
            tint = MaterialTheme.colorScheme.white,
            modifier = Modifier
                .padding(16.dp)
                .size(40.dp)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            text = repository.name,
            color = MaterialTheme.colorScheme.white,
            style = MaterialTheme.typography.bodyLarge
        )
        if (repository.description.isNotEmpty()) {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .rotate(rotation),
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Expand repository icon",
                tint = MaterialTheme.colorScheme.white
            )
        }
    }
}

@Composable
fun RepositoryDescription(description: String) {
    if (description.isNotEmpty())
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .padding(16.dp)
        ) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.white,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 10
            )
        }
}
