package dev.vinigouveia.git_hub_challenge.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Blue = Color(0xFF146FB6)
val LightBlue = Color(0xFF6B92B1)

val Red = Color(0xFFBB1717)
val LightGray = Color(0xFFECECEC)

val White = Color(0xFFFFFFFF)

val Black = Color(0xFF000000)

val ColorScheme.lightGray: Color
    @Composable
    get() = LightGray

val ColorScheme.black: Color
    @Composable
    get() = Black

val ColorScheme.white: Color
    @Composable
    get() = White

val ColorScheme.red: Color
    @Composable
    get() = Red
