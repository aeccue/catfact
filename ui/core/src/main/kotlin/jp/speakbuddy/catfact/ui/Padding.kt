package jp.speakbuddy.catfact.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Spacing(
    val extraSmall: Dp = Defaults.ExtraSmall,
    val small: Dp = Defaults.Small,
    val medium: Dp = Defaults.Medium,
    val large: Dp = Defaults.Large,
    val extraLarge: Dp = Defaults.ExtraLarge
) {

    internal object Defaults {

        val ExtraSmall: Dp = 4.dp
        val Small: Dp = 8.dp
        val Medium: Dp = 16.dp
        val Large: Dp = 24.dp
        val ExtraLarge: Dp = 28.dp
    }
}

val LocalSpacing = staticCompositionLocalOf { Spacing() }

/**
 * Used to provide common paddings in addition to MaterialTheme
 */
@Composable
fun ProvideSpacing(spacing: Spacing, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalSpacing provides spacing, content = content)
}
