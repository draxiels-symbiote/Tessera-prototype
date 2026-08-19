package com.example.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.AdjustBottomSheet
import com.example.ui.components.HairMatchSlot
import com.example.ui.components.HeroGarmentCard
import com.example.ui.components.LivingCaptionBox
import com.example.ui.components.LockItInButton
import com.example.ui.components.TesseraBottomNavBar
import com.example.ui.components.TesseraTopAppBar
import com.example.ui.components.VibeSelectorBar
import com.example.ui.navigation.Screen
import com.example.ui.theme.TesseraThemeHelper
import com.example.ui.viewmodel.TesseraViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: TesseraViewModel,
    onNavigate: (String) -> Unit,
    onNavigateToItemDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.homeUiState.collectAsState()
    val bottomItems by viewModel.allWardrobeItems.collectAsState()
    val colors = TesseraThemeHelper.colors
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            TesseraBottomNavBar(
                currentRoute = Screen.Home.route,
                onNavigate = onNavigate
            )
        },
        containerColor = colors.bg,
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                !uiState.isConnected -> {
                    // Offline Connect View
                    OfflineConnectView(
                        onRetry = { viewModel.toggleConnectionState(true) }
                    )
                }
                uiState.isClosetEmpty -> {
                    // Zero-Gap Empty State
                    HomeEmptyStateView(
                        weatherText = "${uiState.profile?.weatherTemp ?: "72°"} · ${uiState.profile?.weatherLocation ?: "Abuja"}",
                        onAddItemClick = { onNavigate(Screen.AddItemCapture.route) }
                    )
                }
                else -> {
                    // Production Home View
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Top App Bar
                        TesseraTopAppBar(
                            streakDays = uiState.profile?.streakDays ?: 14,
                            weatherText = "${uiState.profile?.weatherTemp ?: "72°"} ${uiState.profile?.weatherCondition ?: "Dry"} · ${uiState.profile?.weatherLocation ?: "Abuja"}"
                        )

                        // Kinetic Vibe Selector Chips
                        VibeSelectorBar(
                            selectedVibe = uiState.selectedVibe,
                            onVibeSelected = { viewModel.setVibe(it) }
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Content Canvas
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            // Hero Outfit Card
                            uiState.currentOutfit?.let { outfit ->
                                HeroGarmentCard(
                                    outfit = outfit,
                                    selectedPage = uiState.activePage,
                                    pageCount = 4,
                                    onPageSelected = { viewModel.setActivePage(it) },
                                    onClick = {
                                        // Tap on garment opens detail
                                        onNavigateToItemDetail(1L)
                                    }
                                )
                            }

                            // Hair Match Slot
                            HairMatchSlot(
                                stylingText = uiState.currentOutfit?.hairStyleName
                                    ?: "Today's styling: half up, low and loose",
                                onClick = { onNavigate(Screen.HairSetup.route) }
                            )

                            // Living Caption Box (Prompt Engine)
                            LivingCaptionBox(
                                promptText = "Ask Tessera to change something",
                                onAdjustClick = { viewModel.setAdjustSheetOpen(true) }
                            )

                            // Primary CTA (LOCK IT IN)
                            LockItInButton(
                                isLocked = uiState.isLockedToday,
                                onLockClick = {
                                    viewModel.lockTodayFit()
                                    scope.launch {
                                        snackbarHostState.showSnackbar("Fit locked for today! Ember streak +1 🔥")
                                    }
                                },
                                modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                            )
                        }
                    }
                }
            }

            // Adjust Occasion / Pieces Bottom Sheet
            if (uiState.isAdjustSheetOpen) {
                AdjustBottomSheet(
                    onDismiss = { viewModel.setAdjustSheetOpen(false) },
                    onApplyOccasion = { occasion, leanVibe ->
                        viewModel.applyOccasion(occasion, leanVibe)
                        scope.launch {
                            snackbarHostState.showSnackbar("Applying $leanVibe styling for today.")
                        }
                    },
                    onSelectBottomPiece = { bottomItem ->
                        viewModel.swapBottomPiece(bottomItem)
                        scope.launch {
                            snackbarHostState.showSnackbar("Swapped bottom to ${bottomItem.name}")
                        }
                    },
                    onTriggerWildcard = {
                        viewModel.triggerWildcard()
                        scope.launch {
                            snackbarHostState.showSnackbar("Curated Wildcard Fit loaded!")
                        }
                    },
                    bottomOptions = bottomItems.filter { it.category == "Bottom" || it.id in listOf(2L, 4L, 5L) },
                    selectedBottomId = 2L
                )
            }
        }
    }
}

@Composable
fun HomeEmptyStateView(
    weatherText: String,
    onAddItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = TesseraThemeHelper.colors

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "TESSERA",
                color = colors.textPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 2.sp
            )
            Text(
                text = weatherText,
                color = colors.textSecondary,
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Hero Empty Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .shadow(12.dp, RoundedCornerShape(32.dp))
                .clip(RoundedCornerShape(32.dp))
                .background(colors.surface)
                .border(1.dp, colors.divider, RoundedCornerShape(32.dp))
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Dashed Canvas
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(RoundedCornerShape(24.dp))
                        .background(colors.bg)
                        .border(
                            width = 2.dp,
                            color = colors.divider.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(24.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .background(colors.primaryContainer.copy(alpha = 0.08f))
                    )
                    Icon(
                        imageVector = Icons.Filled.Checkroom,
                        contentDescription = "Empty hanger",
                        tint = colors.textSecondary.copy(alpha = 0.4f),
                        modifier = Modifier.size(64.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Copy Stack
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Your closet's still filling in",
                        color = colors.textPrimary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Add 2 more items and Tessera can start building your first fit.",
                        color = colors.textMuted,
                        fontSize = 13.5.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 19.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // CTA Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .shadow(12.dp, CircleShape)
                        .clip(CircleShape)
                        .background(colors.primaryGradient)
                        .clickable(onClick = onAddItemClick)
                        .testTag("empty_state_add_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "+ Add an item",
                        color = colors.onPrimaryContainer,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun OfflineConnectView(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = TesseraThemeHelper.colors

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top Brand Wordmark
        Text(
            text = "TESSERA",
            color = colors.textPrimary,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 2.sp,
            modifier = Modifier.padding(top = 12.dp)
        )

        // Center Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(16.dp, RoundedCornerShape(36.dp))
                .clip(RoundedCornerShape(36.dp))
                .background(colors.surface)
                .border(1.dp, colors.divider, RoundedCornerShape(36.dp))
                .padding(32.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Graphic Canvas
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.2f)
                        .clip(RoundedCornerShape(28.dp))
                        .background(colors.bg)
                        .border(1.dp, colors.divider, RoundedCornerShape(28.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.CloudOff,
                        contentDescription = "Offline cloud",
                        tint = colors.primaryContainer,
                        modifier = Modifier.size(72.dp)
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "Connect to set up your closet",
                    color = colors.textPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Tessera needs a connection to load your wardrobe and start building today's fit.",
                    color = colors.textMuted,
                    fontSize = 13.5.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Try Again CTA
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .shadow(8.dp, CircleShape)
                        .clip(CircleShape)
                        .background(colors.primaryGradient)
                        .clickable(onClick = onRetry)
                        .testTag("retry_connection_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Try again",
                        color = colors.onPrimaryContainer,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(14.dp),
                        strokeWidth = 2.dp,
                        color = colors.textSecondary.copy(alpha = 0.5f)
                    )
                    Text(
                        text = "Checking connection automatically...",
                        color = colors.textSecondary.copy(alpha = 0.6f),
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
