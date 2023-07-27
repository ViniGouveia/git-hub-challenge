package dev.vinigouveia.git_hub_challenge.utils.screens

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.vinigouveia.git_hub_challenge.R
import org.junit.Rule
import org.junit.Test

class EmptyListScreenTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun checkEmptyUsersListText() {
        rule.setContent { EmptyListScreen(text = R.string.empty_users_list_error_message) }
        val screenText = rule.activity.getString(R.string.empty_users_list_error_message)

        rule.onNodeWithText(screenText).assertExists().assertIsDisplayed()
    }

    @Test
    fun checkEmptyRepositoriesListText() {
        rule.setContent { EmptyListScreen(text = R.string.empty_repositories_list_error_message) }
        val screenText = rule.activity.getString(R.string.empty_repositories_list_error_message)

        rule.onNodeWithText(screenText).assertExists().assertIsDisplayed()
    }
}
