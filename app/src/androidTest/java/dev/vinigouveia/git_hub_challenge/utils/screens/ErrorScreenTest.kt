package dev.vinigouveia.git_hub_challenge.utils.screens

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dev.vinigouveia.git_hub_challenge.R
import org.junit.Assert
import org.junit.Rule
import org.junit.Test


class ErrorScreenTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun checkConnectionErrorText() {
        rule.setContent { ErrorScreen(message = R.string.connection_error_message) {} }
        val errorString = rule.activity.getString(R.string.connection_error_message)

        rule.onNodeWithText(errorString).assertExists().assertIsDisplayed()
    }

    @Test
    fun checkUnknownErrorText() {
        rule.setContent { ErrorScreen(message = R.string.unknown_error_message) {} }
        val errorString = rule.activity.getString(R.string.unknown_error_message)

        rule.onNodeWithText(errorString).assertExists().assertIsDisplayed()
    }

    @Test
    fun checkClickAction() {
        var isClicked = false
        rule.setContent {
            ErrorScreen(message = R.string.unknown_error_message) {
                isClicked = true
            }
        }

        rule.onNode(hasClickAction()).performClick()

        Assert.assertEquals(isClicked, true)
    }
}
