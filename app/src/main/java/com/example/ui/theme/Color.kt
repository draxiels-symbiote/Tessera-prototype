package com.example.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Obsidian Rose Theme Colors
val ObsidianRoseBg = Color(0xFF0B0A0C)
val ObsidianRoseSurface = Color(0xFF17141A)
val ObsidianRoseSurfaceAlt = Color(0xFF1F1A21)
val ObsidianRoseSurfaceBright = Color(0xFF3A383B)
val ObsidianRoseContainerHigh = Color(0xFF2B292C)
val ObsidianRoseContainerLowest = Color(0xFF0F0E10)
val ObsidianRosePrimary = Color(0xFFFFD2E2)
val ObsidianRosePrimaryContainer = Color(0xFFE8B4C8)
val ObsidianRoseOnPrimaryContainer = Color(0xFF3A1420)
val ObsidianRoseAccentStrong = Color(0xFFD98FAA)
val ObsidianRoseTextPrimary = Color(0xFFF5F0F0)
val ObsidianRoseTextSecondary = Color(0xFFB8AEB2)
val ObsidianRoseTextMuted = Color(0xFF8F8993)
val ObsidianRoseDivider = Color(0x14FFFFFF)
val ObsidianRoseActiveCard = Color(0xFF221B26)

// Emerald Eclipse Theme Colors
val EmeraldEclipseBg = Color(0xFF060B08)
val EmeraldEclipseSurface = Color(0xFF101815)
val EmeraldEclipseSurfaceAlt = Color(0xFF16211C)
val EmeraldEclipsePrimary = Color(0xFF41E5AB)
val EmeraldEclipsePrimaryContainer = Color(0xFF39E0A6)
val EmeraldEclipseOnPrimaryContainer = Color(0xFF003826)
val EmeraldEclipseAccentStrong = Color(0xFF1FB582)
val EmeraldEclipseTextPrimary = Color(0xFFF5F0F0)
val EmeraldEclipseTextSecondary = Color(0xFFA0B0A8)
val EmeraldEclipseTextMuted = Color(0xFF7A8B83)

// Success, Error, Accents
val AccentMint = Color(0xFF2ED9A0)
val AccentFlame = Color(0xFFE8B4C8)
val AccentError = Color(0xFFFFB4AB)
val DarkOverlay = Color(0xCC0B0A0C)

val ObsidianRoseGradient = Brush.horizontalGradient(
    colors = listOf(Color(0xFFE8B4C8), Color(0xFFD98FAA))
)

val EmeraldEclipseGradient = Brush.horizontalGradient(
    colors = listOf(Color(0xFF41E5AB), Color(0xFF1FB582))
)

val DarkRadialGradient = Brush.radialGradient(
    colors = listOf(Color(0xFF221820), Color(0xFF0B0A0C)),
    radius = 1200f
)
