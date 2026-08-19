package com.example.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ui.components.TesseraBottomNavBar
import com.example.ui.navigation.Screen
import com.example.ui.theme.TesseraThemeHelper
import com.example.ui.viewmodel.TesseraViewModel
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    viewModel: TesseraViewModel,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val profile by viewModel.userProfile.collectAsState()
    val colors = TesseraThemeHelper.colors
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val currentTheme = profile?.selectedTheme ?: "Obsidian Rose"
    val avatarUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuD1wR1UTdeM050Sd2zgIEEpbGWfWUzyYbx-ghUQzdB6RwVz20ucXw-Wljr95QI_ccpV-G_BYs1fN2ErIKEx3RkQe7020hATuHFtOW5W7ItauRCNaR752khCQP3bCdIpo_9_c6OorBqHnYtJO2pKvsU5FiO-xckqDD9ncadHJLM-kRYkRfRwVEaoWR5ElSKqOM9wCBqIe6Ggb3oJgDaLIPvqiq3RIjDGmLFm1xSKncUomQXdKs2p-jaY"

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            TesseraBottomNavBar(
                currentRoute = Screen.Settings.route,
                onNavigate = onNavigate
            )
        },
        containerColor = colors.bg,
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Screen Title
            Text(
                text = "Control Center",
                color = colors.textPrimary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            // User Profile Card
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(6.dp, RoundedCornerShape(24.dp))
                    .clip(RoundedCornerShape(24.dp))
                    .background(colors.surface)
                    .border(1.dp, colors.divider, RoundedCornerShape(24.dp))
                    .padding(18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(colors.surfaceAlt)
                        .border(2.dp, colors.primaryContainer, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(avatarUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Profile Avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = profile?.name ?: "Kester",
                        color = colors.textPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "${profile?.streakDays ?: 14}-day ember streak 🔥",
                        color = colors.primaryContainer,
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Abuja, Nigeria · Member since 2024",
                        color = colors.textSecondary,
                        fontSize = 11.5.sp
                    )
                }
            }

            // APPEARANCE THEME SELECTOR
            Column {
                Text(
                    text = "APPEARANCE THEME",
                    color = colors.textSecondary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Obsidian Rose Theme Card
                    ThemeSelectionCard(
                        themeName = "Obsidian Rose",
                        accentGradient = Brush.horizontalGradient(listOf(Color(0xFFE8B4C8), Color(0xFFD98FAA))),
                        surfaceColor = Color(0xFF17141A),
                        isSelected = currentTheme == "Obsidian Rose",
                        onSelect = { viewModel.updateTheme("Obsidian Rose") },
                        modifier = Modifier.weight(1f)
                    )

                    // Emerald Eclipse Theme Card
                    ThemeSelectionCard(
                        themeName = "Emerald Eclipse",
                        accentGradient = Brush.horizontalGradient(listOf(Color(0xFF41E5AB), Color(0xFF1FB582))),
                        surfaceColor = Color(0xFF101815),
                        isSelected = currentTheme == "Emerald Eclipse",
                        onSelect = { viewModel.updateTheme("Emerald Eclipse") },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // STYLE PROFILE SECTION
            Column {
                Text(
                    text = "STYLE PROFILE",
                    color = colors.textSecondary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(colors.surface)
                        .border(1.dp, colors.divider, RoundedCornerShape(20.dp))
                ) {
                    SettingsNavigationRow(
                        title = "Head Shape Profile",
                        value = "${profile?.headShape ?: "Oval"} Shape",
                        onClick = { onNavigate(Screen.HairSetup.route) }
                    )

                    HorizontalDivider(color = colors.divider)

                    SettingsNavigationRow(
                        title = "Current Hairstyle",
                        value = profile?.currentHairstyle ?: "Textured crop",
                        onClick = { onNavigate(Screen.Closet.route) }
                    )
                }
            }

            // WARDROBE ACTIONS
            Column {
                Text(
                    text = "WARDROBE & AVAILABILITY",
                    color = colors.textSecondary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(colors.surface)
                        .border(1.dp, colors.divider, RoundedCornerShape(20.dp))
                ) {
                    SettingsActionRow(
                        title = "Mark everything available",
                        subtitle = "Resets lent or in-wash pieces to available",
                        onClick = {
                            viewModel.markAllAvailable()
                            scope.launch {
                                snackbarHostState.showSnackbar("All pieces marked available!")
                            }
                        }
                    )

                    HorizontalDivider(color = colors.divider)

                    SettingsNavigationRow(
                        title = "Weather Location",
                        value = "Auto · Abuja (72°)",
                        onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar("Weather synced with Abuja station.")
                            }
                        }
                    )
                }
            }

            // PROTOTYPE & DEMO CONTROLS
            Column {
                Text(
                    text = "PROTOTYPE DEMO CONTROLS",
                    color = colors.textSecondary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(colors.surface)
                        .border(1.dp, colors.divider, RoundedCornerShape(20.dp))
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    // Empty closet mode switch
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Zero-Gap Empty State (v4.0.0)",
                                color = colors.textPrimary,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "Simulate a new user with empty closet",
                                color = colors.textSecondary,
                                fontSize = 11.5.sp
                            )
                        }

                        Switch(
                            checked = profile?.isClosetEmptyMode ?: false,
                            onCheckedChange = { viewModel.toggleEmptyState(it) },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = colors.onPrimaryContainer,
                                checkedTrackColor = colors.primaryContainer,
                                uncheckedThumbColor = colors.textSecondary,
                                uncheckedTrackColor = colors.surfaceAlt
                            )
                        )
                    }

                    HorizontalDivider(color = colors.divider)

                    // Offline mode switch
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Offline Connection State",
                                color = colors.textPrimary,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "Simulate offline connect recovery screen",
                                color = colors.textSecondary,
                                fontSize = 11.5.sp
                            )
                        }

                        Switch(
                            checked = !(profile?.isConnected ?: true),
                            onCheckedChange = { viewModel.toggleConnectionState(!it) },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = colors.onPrimaryContainer,
                                checkedTrackColor = colors.primaryContainer,
                                uncheckedThumbColor = colors.textSecondary,
                                uncheckedTrackColor = colors.surfaceAlt
                            )
                        )
                    }

                    HorizontalDivider(color = colors.divider)

                    // Reset Data Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.resetSampleData()
                                scope.launch {
                                    snackbarHostState.showSnackbar("Sample data restored to defaults.")
                                }
                            }
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.RestartAlt,
                            contentDescription = null,
                            tint = colors.primaryContainer,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "Reset All Sample Outfits & Timeline",
                            color = colors.primaryContainer,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
private fun ThemeSelectionCard(
    themeName: String,
    accentGradient: Brush,
    surfaceColor: Color,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = TesseraThemeHelper.colors

    Box(
        modifier = modifier
            .testTag("theme_card_${themeName.lowercase().replace(" ", "_")}")
            .shadow(if (isSelected) 8.dp else 2.dp, RoundedCornerShape(18.dp))
            .clip(RoundedCornerShape(18.dp))
            .background(surfaceColor)
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) colors.primaryContainer else colors.divider,
                shape = RoundedCornerShape(18.dp)
            )
            .clickable(onClick = onSelect)
            .padding(14.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Color swatch
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(accentGradient)
                )

                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(colors.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            tint = colors.onPrimaryContainer,
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = themeName,
                color = colors.textPrimary,
                fontSize = 13.5.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun SettingsNavigationRow(
    title: String,
    value: String,
    onClick: () -> Unit
) {
    val colors = TesseraThemeHelper.colors

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = colors.textPrimary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = value,
                color = colors.textSecondary,
                fontSize = 13.sp
            )
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowForwardIos,
                contentDescription = null,
                tint = colors.textSecondary,
                modifier = Modifier.size(12.dp)
            )
        }
    }
}

@Composable
private fun SettingsActionRow(
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    val colors = TesseraThemeHelper.colors

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Text(
            text = title,
            color = colors.textPrimary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = subtitle,
            color = colors.textSecondary,
            fontSize = 12.sp
        )
    }
}
