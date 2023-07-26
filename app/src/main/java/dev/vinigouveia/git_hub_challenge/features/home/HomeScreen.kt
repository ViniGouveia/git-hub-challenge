package dev.vinigouveia.git_hub_challenge.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.vinigouveia.git_hub_challenge.R
import dev.vinigouveia.git_hub_challenge.data.model.UserBO
import dev.vinigouveia.git_hub_challenge.features.home.composables.UsersListItem
import dev.vinigouveia.git_hub_challenge.ui.theme.lightGray
import dev.vinigouveia.git_hub_challenge.ui.theme.white
import dev.vinigouveia.git_hub_challenge.utils.components.CustomDivider
import dev.vinigouveia.git_hub_challenge.utils.components.CustomTopAppBar
import dev.vinigouveia.git_hub_challenge.utils.components.SearchBarCompose
import dev.vinigouveia.git_hub_challenge.utils.getErrorMessage
import dev.vinigouveia.git_hub_challenge.utils.screens.EmptyListScreen
import dev.vinigouveia.git_hub_challenge.utils.screens.ErrorScreen
import dev.vinigouveia.git_hub_challenge.utils.screens.LoadingScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigationCallback: (String) -> Unit
) {
    val screenState by viewModel.screenState.collectAsState()
    viewModel.handleIntent(HomeIntent.FetchUsers)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.lightGray,
        topBar = {
            CustomTopAppBar(Icon = {
                Icon(
                    painter = painterResource(id = R.drawable.github_logo_white),
                    contentDescription = "Home icon",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp),
                    tint = MaterialTheme.colorScheme.white
                )
            })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            SearchBarCompose(Modifier.padding(12.dp)) { searchParam ->
                viewModel.handleIntent(HomeIntent.SearchUser(searchParam))
            }

            CustomDivider()

            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                color = MaterialTheme.colorScheme.lightGray
            ) {
                when {
                    screenState.isLoading -> LoadingScreen()
                    screenState.data.isNotEmpty() -> UsersList(usersList = screenState.data) {
                        navigationCallback(it.username)
                    }

                    screenState.error != null -> ErrorScreen(
                        message = screenState.error.getErrorMessage()
                    ) {
                        viewModel.handleIntent(HomeIntent.FetchUsers)
                    }

                    else -> EmptyListScreen(text = R.string.empty_users_list_error_message)
                }
            }

            CustomDivider(Modifier.padding(vertical = 16.dp))

        } // Column
    } // Scaffold
}

@Composable
fun UsersList(usersList: List<UserBO>, onClick: (UserBO) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(12.dp)) {
        items(usersList) { user ->
            UsersListItem(user = user) {
                onClick(user)
            }
        }
    }
}
