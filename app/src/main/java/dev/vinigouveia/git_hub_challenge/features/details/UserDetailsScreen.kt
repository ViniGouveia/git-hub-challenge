package dev.vinigouveia.git_hub_challenge.features.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.vinigouveia.git_hub_challenge.R
import dev.vinigouveia.git_hub_challenge.data.model.RepositoryBO
import dev.vinigouveia.git_hub_challenge.data.model.UserDetailsBO
import dev.vinigouveia.git_hub_challenge.features.details.composables.UserInfoCard
import dev.vinigouveia.git_hub_challenge.features.details.composables.UserRepositoryItem
import dev.vinigouveia.git_hub_challenge.ui.theme.lightGray
import dev.vinigouveia.git_hub_challenge.ui.theme.white
import dev.vinigouveia.git_hub_challenge.utils.components.CustomTopAppBar
import dev.vinigouveia.git_hub_challenge.utils.components.SearchBarCompose
import dev.vinigouveia.git_hub_challenge.utils.getErrorMessage
import dev.vinigouveia.git_hub_challenge.utils.screens.EmptyListScreen
import dev.vinigouveia.git_hub_challenge.utils.screens.ErrorScreen
import dev.vinigouveia.git_hub_challenge.utils.screens.LoadingScreen

@Composable
fun UserDetailsScreen(
    viewModel: UserDetailsViewModel = hiltViewModel(),
    navigationCallback: () -> Unit
) {
    val screenState by viewModel.screenState.collectAsState()
    val scrollState = rememberLazyListState()

    viewModel.handleIntent(UserDetailsIntent.FetchUserInfo)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(Icon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back icon",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp)
                        .clickable { navigationCallback() },
                    tint = MaterialTheme.colorScheme.white
                )
            })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .background(color = MaterialTheme.colorScheme.lightGray)
                .fillMaxHeight()
        ) {
            when {
                screenState.data != null -> {
                    UserDetailsContent(
                        user = screenState.data!!,
                        scrollState = scrollState
                    ) { searchParam ->
                        viewModel.handleIntent(UserDetailsIntent.SearchRepository(searchParam))
                    }
                }

                screenState.error != null -> ErrorScreen(message = screenState.error.getErrorMessage()) {
                    viewModel.handleIntent(UserDetailsIntent.FetchUserInfo)
                }

                screenState.isLoading -> LoadingScreen()
            }
        } // Column
    } // Scaffold
}

@Composable
fun UserDetailsContent(
    user: UserDetailsBO,
    scrollState: LazyListState,
    searchCallback: (String) -> Unit
) {
    UserInfoCard(userDetails = user, scrollState)
    SearchBarCompose(
        Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    ) { searchParam -> searchCallback(searchParam) }
    if (user.publicReposList.isEmpty()) {
        EmptyListScreen(text = R.string.empty_repositories_list_error_message)
    } else {
        UserRepositoriesList(
            data = user.publicReposList,
            scrollState = scrollState
        )
    }
}

@Composable
fun UserRepositoriesList(
    data: List<RepositoryBO>,
    scrollState: LazyListState
) {
    LazyColumn(state = scrollState, contentPadding = PaddingValues(bottom = 8.dp)) {
        items(data) { repository ->
            UserRepositoryItem(data = repository)
        }
    }
}
