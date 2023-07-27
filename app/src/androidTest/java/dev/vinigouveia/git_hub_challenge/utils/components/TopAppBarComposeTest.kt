package dev.vinigouveia.git_hub_challenge.utils.components

import androidx.activity.ComponentActivity
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import dev.vinigouveia.git_hub_challenge.R
import org.junit.Rule
import org.junit.Test

class TopAppBarComposeTest {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun checkTopBarComponents() {
        rule.setContent {
            CustomTopAppBar {
                Icon(
                    painter = painterResource(id = R.drawable.github_logo_black),
                    contentDescription = "icon test"
                )
            }
        }
        val topBarText = rule.activity.getString(R.string.app_name)

        rule.onNodeWithText(topBarText).assertExists().assertIsDisplayed()
        rule.onNodeWithContentDescription("icon test").assertExists().assertIsDisplayed()
    }
}
