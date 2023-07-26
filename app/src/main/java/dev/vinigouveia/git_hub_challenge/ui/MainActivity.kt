package dev.vinigouveia.git_hub_challenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dev.vinigouveia.git_hub_challenge.features.details.UserDetailsScreen
import dev.vinigouveia.git_hub_challenge.features.home.HomeScreen
import dev.vinigouveia.git_hub_challenge.ui.theme.GitHubChallengeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubChallengeTheme {
                NavigationBuilder()
            }
        }
    }
}

@Composable
fun NavigationBuilder() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home_screen") {
        composable(route = "home_screen") {
            HomeScreen { username ->
                navController.navigate(route = "user_details/$username")
            }
        }
        composable(
            route = "user_details/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) {
            UserDetailsScreen {
                navController.popBackStack()
            }
        }
    }
}
