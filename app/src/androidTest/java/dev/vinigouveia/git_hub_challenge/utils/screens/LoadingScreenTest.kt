package dev.vinigouveia.git_hub_challenge.utils.screens

import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class LoadingScreenTest {

    @get:Rule
    val rule = createComposeRule()

    private val progressBar = hasProgressBarRangeInfo(
        rangeInfo = ProgressBarRangeInfo(
            current = 1f,
            range = 0f..1f,
            steps = 0
        )
    )

    @Test
    fun showLoading() {
        rule.setContent { LoadingScreen() }

        rule.onNode(
            matcher = progressBar
        ).assertExists().assertIsDisplayed()
    }
}
