package com.example.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class TesseraColors(
    val bg: Color,
    val surface: Color,
    val surfaceAlt: Color,
    val surfaceActive: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val accentStrong: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textMuted: Color,
    val divider: Color,
    val primaryGradient: Brush,
    val isObsidianRose: Boolean
)

val LocalTesseraColors = staticCompositionLocalOf {
    TesseraColors(
        bg = ObsidianRoseBg,
        surface = ObsidianRoseSurface,
        surfaceAlt = ObsidianRoseSurfaceAlt,
        surfaceActive = ObsidianRoseActiveCard,
        primaryContainer = ObsidianRosePrimaryContainer,
        onPrimaryContainer = ObsidianRoseOnPrimaryContainer,
        accentStrong = ObsidianRoseAccentStrong,
        textPrimary = ObsidianRoseTextPrimary,
        textSecondary = ObsidianRoseTextSecondary,
        textMuted = ObsidianRoseTextMuted,
        divider = ObsidianRoseDivider,
        primaryGradient = ObsidianRoseGradient,
        isObsidianRose = true
    )
}

val ObsidianRoseColorScheme = darkColorScheme(
    primary = ObsidianRosePrimary,
    onPrimary = ObsidianRoseOnPrimaryContainer,
    primaryContainer = ObsidianRosePrimaryContainer,
    onPrimaryContainer = ObsidianRoseOnPrimaryContainer,
    secondary = ObsidianRoseAccentStrong,
    background = ObsidianRoseBg,
    onBackground = ObsidianRoseTextPrimary,
    surface = ObsidianRoseSurface,
    onSurface = ObsidianRoseTextPrimary,
    surfaceVariant = ObsidianRoseSurfaceAlt,
    onSurfaceVariant = ObsidianRoseTextSecondary,
    outline = ObsidianRoseDivider,
    error = AccentError
)

val EmeraldEclipseColorScheme = darkColorScheme(
    primary = EmeraldEclipsePrimary,
    onPrimary = EmeraldEclipseOnPrimaryContainer,
    primaryContainer = EmeraldEclipsePrimaryContainer,
    onPrimaryContainer = EmeraldEclipseOnPrimaryContainer,
    secondary = EmeraldEclipseAccentStrong,
    background = EmeraldEclipseBg,
    onBackground = EmeraldEclipseTextPrimary,
    surface = EmeraldEclipseSurface,
    onSurface = EmeraldEclipseTextPrimary,
    surfaceVariant = EmeraldEclipseSurfaceAlt,
    onSurfaceVariant = EmeraldEclipseTextSecondary,
    outline = ObsidianRoseDivider,
    error = AccentError
)

@Composable
fun TesseraTheme(
    selectedTheme: String = "Obsidian Rose",
    content: @Composable () -> Unit
) {
    val isRose = selectedTheme != "Emerald Eclipse"
    val colorScheme = if (isRose) ObsidianRoseColorScheme else EmeraldEclipseColorScheme

    val customColors = TesseraColors(
        bg = if (isRose) ObsidianRoseBg else EmeraldEclipseBg,
        surface = if (isRose) ObsidianRoseSurface else EmeraldEclipseSurface,
        surfaceAlt = if (isRose) ObsidianRoseSurfaceAlt else EmeraldEclipseSurfaceAlt,
        surfaceActive = if (isRose) ObsidianRoseActiveCard else Color(0xFF14241D),
        primaryContainer = if (isRose) ObsidianRosePrimaryContainer else EmeraldEclipsePrimaryContainer,
        onPrimaryContainer = if (isRose) ObsidianRoseOnPrimaryContainer else EmeraldEclipseOnPrimaryContainer,
        accentStrong = if (isRose) ObsidianRoseAccentStrong else EmeraldEclipseAccentStrong,
        textPrimary = if (isRose) ObsidianRoseTextPrimary else EmeraldEclipseTextPrimary,
        textSecondary = if (isRose) ObsidianRoseTextSecondary else EmeraldEclipseTextSecondary,
        textMuted = if (isRose) ObsidianRoseTextMuted else EmeraldEclipseTextMuted,
        divider = ObsidianRoseDivider,
        primaryGradient = if (isRose) ObsidianRoseGradient else EmeraldEclipseGradient,
        isObsidianRose = isRose
    )

    CompositionLocalProvider(LocalTesseraColors provides customColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

object TesseraThemeHelper {
    val colors: TesseraColors
        @Composable
        @ReadOnlyComposable
        get() = LocalTesseraColors.current
}
