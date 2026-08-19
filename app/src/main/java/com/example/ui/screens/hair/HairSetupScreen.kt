package com.example.ui.screens.hair

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.TesseraThemeHelper
import com.example.ui.viewmodel.TesseraViewModel

data class HeadShapeInfo(
    val id: String,
    val name: String,
    val description: String,
    val bestStyles: String
)

val headShapesList = listOf(
    HeadShapeInfo(
        id = "Oval",
        name = "Oval",
        description = "Forehead slightly wider than jaw, softly rounded chin.",
        bestStyles = "Textured crop, Pompadour, Quiff"
    ),
    HeadShapeInfo(
        id = "Round",
        name = "Round",
        description = "Equal face width and length, soft curved jawline.",
        bestStyles = "Angular fringe, High fade, Pompadour"
    ),
    HeadShapeInfo(
        id = "Square",
        name = "Square",
        description = "Broad forehead, strong sharp angular jaw.",
        bestStyles = "Buzz cut, Side part, Textured quiff"
    ),
    HeadShapeInfo(
        id = "Heart",
        name = "Heart",
        description = "Wide forehead and cheekbones, narrow pointed chin.",
        bestStyles = "Curly top, Medium fringe, Flow"
    ),
    HeadShapeInfo(
        id = "Diamond",
        name = "Diamond",
        description = "Narrow forehead and jaw, high prominent cheekbones.",
        bestStyles = "Textured crop, Side sweep, Slick back"
    ),
    HeadShapeInfo(
        id = "Oblong",
        name = "Oblong",
        description = "Face is longer than it is wide, straight sides.",
        bestStyles = "Short textured cut, Layered fringe"
    )
)

@Composable
fun HairSetupScreen(
    viewModel: TesseraViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val profile by viewModel.userProfile.collectAsState()
    val colors = TesseraThemeHelper.colors
    var selectedShape by remember(profile) { mutableStateOf(profile?.headShape ?: "Oval") }
    var isGuideExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.testTag("hair_setup_back")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = colors.textPrimary
                    )
                }
                Text(
                    text = "Hair Setup (v4.0.0)",
                    color = colors.textPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.bg)
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Confirm Shape Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .shadow(12.dp, CircleShape)
                        .clip(CircleShape)
                        .background(colors.primaryGradient)
                        .clickable {
                            viewModel.updateHeadShape(selectedShape)
                            onBack()
                        }
                        .testTag("confirm_shape_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Confirm Shape ->",
                        color = colors.onPrimaryContainer,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(
                    onClick = onBack,
                    modifier = Modifier.testTag("skip_shape_button")
                ) {
                    Text(
                        text = "Not sure — I'll figure it out later",
                        color = colors.textSecondary,
                        fontSize = 13.sp
                    )
                }
            }
        },
        containerColor = colors.bg,
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "Which shape fits you best?",
                color = colors.textPrimary,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Compare your forehead, cheekbones, and jaw — whichever's widest is your best clue.",
                color = colors.textMuted,
                fontSize = 13.5.sp,
                lineHeight = 19.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Expandable 10-second guide
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(colors.surfaceAlt)
                    .border(1.dp, colors.divider, RoundedCornerShape(14.dp))
                    .clickable { isGuideExpanded = !isGuideExpanded }
                    .padding(14.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.HelpOutline,
                                contentDescription = "Help",
                                tint = colors.primaryContainer,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Not sure? Tap for a 10-second check",
                                color = colors.primaryContainer,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Icon(
                            imageVector = if (isGuideExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                            contentDescription = null,
                            tint = colors.primaryContainer,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    AnimatedVisibility(visible = isGuideExpanded) {
                        Column(modifier = Modifier.padding(top = 10.dp)) {
                            Text(
                                text = "1. Stand in front of a mirror with hair pushed back.\n2. Note if your face is longer than wide (Oval/Oblong) or roughly equal (Round/Square).\n3. Check your jawline: curved points to Oval/Round, sharp angle points to Square/Diamond.",
                                color = colors.textSecondary,
                                fontSize = 12.5.sp,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Shape Cards Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(headShapesList) { shape ->
                    val isSelected = shape.name.equals(selectedShape, ignoreCase = true)
                    HeadShapeCard(
                        shape = shape,
                        isSelected = isSelected,
                        onClick = { selectedShape = shape.name }
                    )
                }
            }
        }
    }
}

@Composable
private fun HeadShapeCard(
    shape: HeadShapeInfo,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = TesseraThemeHelper.colors
    val borderColor by animateColorAsState(
        targetValue = if (isSelected) colors.primaryContainer else colors.divider,
        label = "shapeBorder"
    )
    val cardBg by animateColorAsState(
        targetValue = if (isSelected) colors.surfaceActive else colors.surface,
        label = "shapeBg"
    )

    Box(
        modifier = modifier
            .testTag("head_shape_${shape.name.lowercase()}")
            .then(
                if (isSelected) {
                    Modifier.shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(20.dp),
                        ambientColor = colors.primaryContainer.copy(alpha = 0.2f),
                        spotColor = colors.primaryContainer.copy(alpha = 0.4f)
                    )
                } else Modifier
            )
            .clip(RoundedCornerShape(20.dp))
            .background(cardBg)
            .border(if (isSelected) 2.dp else 1.dp, borderColor, RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .padding(14.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = shape.name,
                    color = if (isSelected) colors.primaryContainer else colors.textPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
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
                            contentDescription = "Selected",
                            tint = colors.onPrimaryContainer,
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Silhouette icon box
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(colors.surfaceAlt)
                    .border(1.dp, colors.divider, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Face,
                    contentDescription = shape.name,
                    tint = if (isSelected) colors.primaryContainer else colors.textSecondary,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = shape.description,
                color = colors.textSecondary,
                fontSize = 11.5.sp,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp,
                minLines = 3
            )
        }
    }
}
