package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.local.WardrobeItemEntity
import com.example.ui.theme.TesseraThemeHelper

enum class AdjustMode {
    OCCASION,
    SWAP_PIECES
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdjustBottomSheet(
    onDismiss: () -> Unit,
    onApplyOccasion: (String, String) -> Unit,
    onSelectBottomPiece: (WardrobeItemEntity) -> Unit,
    onTriggerWildcard: () -> Unit,
    bottomOptions: List<WardrobeItemEntity>,
    selectedBottomId: Long,
    modifier: Modifier = Modifier
) {
    val colors = TesseraThemeHelper.colors
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var currentMode by remember { mutableStateOf(AdjustMode.OCCASION) }
    var occasionText by remember { mutableStateOf("") }
    var selectedLeanVibe by remember { mutableStateOf("Serious") }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = colors.surface,
        scrimColor = Color.Black.copy(alpha = 0.75f),
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .width(36.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(colors.divider.copy(alpha = 0.5f))
            )
        },
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Mode toggle header (Occasion vs Swap Pieces)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(colors.surfaceAlt)
                        .padding(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (currentMode == AdjustMode.OCCASION) colors.primaryContainer else Color.Transparent)
                            .clickable { currentMode = AdjustMode.OCCASION }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "Occasion",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (currentMode == AdjustMode.OCCASION) colors.onPrimaryContainer else colors.textSecondary
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (currentMode == AdjustMode.SWAP_PIECES) colors.primaryContainer else Color.Transparent)
                            .clickable { currentMode = AdjustMode.SWAP_PIECES }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "Swap Pieces",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (currentMode == AdjustMode.SWAP_PIECES) colors.onPrimaryContainer else colors.textSecondary
                        )
                    }
                }

                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(colors.surfaceAlt)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close",
                        tint = colors.textSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            if (currentMode == AdjustMode.OCCASION) {
                // OCCASION OVERRIDE VIEW
                Text(
                    text = "Got something specific today?",
                    color = colors.textPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "This only applies to today's fit.",
                    color = colors.textMuted,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(18.dp))

                // What's happening input
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "WHAT'S HAPPENING",
                        color = colors.textSecondary,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        modifier = Modifier.padding(start = 4.dp, bottom = 6.dp)
                    )
                    OutlinedTextField(
                        value = occasionText,
                        onValueChange = { occasionText = it },
                        placeholder = {
                            Text(
                                text = "e.g. Match tonight, interview",
                                color = colors.textSecondary.copy(alpha = 0.5f),
                                fontSize = 13.sp
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = colors.surfaceAlt,
                            unfocusedContainerColor = colors.surfaceAlt,
                            focusedBorderColor = colors.primaryContainer,
                            unfocusedBorderColor = colors.divider,
                            focusedTextColor = colors.textPrimary,
                            unfocusedTextColor = colors.textPrimary
                        ),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("occasion_input_field")
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Lean toward vibe selector
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "LEAN TOWARD",
                        color = colors.textSecondary,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("Serious", "Attractive", "Drippy").forEach { vibe ->
                            val isSelected = vibe == selectedLeanVibe
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(999.dp))
                                    .background(if (isSelected) colors.primaryContainer else colors.surfaceAlt)
                                    .border(1.dp, if (isSelected) colors.primaryContainer else colors.divider, RoundedCornerShape(999.dp))
                                    .clickable { selectedLeanVibe = vibe }
                                    .padding(vertical = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = vibe,
                                    color = if (isSelected) colors.onPrimaryContainer else colors.textSecondary,
                                    fontSize = 13.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Apply Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .shadow(8.dp, CircleShape)
                        .clip(CircleShape)
                        .background(colors.primaryGradient)
                        .clickable {
                            onApplyOccasion(occasionText, selectedLeanVibe)
                            onDismiss()
                        }
                        .testTag("apply_occasion_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Apply for today",
                        color = colors.onPrimaryContainer,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                // SWAP PIECES VIEW
                Text(
                    text = "Adjust Outfit",
                    color = colors.textPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Curated alternatives for today's vibe",
                    color = colors.textSecondary,
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Collapsed Top Row
                var isTopExpanded by remember { mutableStateOf(false) }
                PieceSwapRow(
                    label = "TOP",
                    itemName = "Oversized Boxy Tee (Charcoal)",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDpbXH_IGIqwGihYBGVBQKp8SSfdy2zbxJJXPzQIyABaQBy1u9zpWdksWuooqWiUaeVnuYGbVs_vvburapdXq95ETsdtAkAoNuCu9F4lb65ns6JQWhHpyTqPr2oUuB-S8bTt2VD5zY-lxQZPwAzm3edxSvTYYpWlfkRd37PCm618HtUYlXCNB7RvkW9_IYzp8P5D2sZJnVjleUaEVqgOSBbUkNvRFgpCFYI8oNRcH-zRJEAE6sP5mfK",
                    isExpanded = isTopExpanded,
                    onToggle = { isTopExpanded = !isTopExpanded }
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Expanded Bottom Row
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(colors.surfaceAlt)
                        .border(1.dp, colors.primaryContainer.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(colors.surface)
                                .padding(14.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "BOTTOM (ACTIVE)",
                                color = colors.primaryContainer,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                            Icon(
                                imageVector = Icons.Filled.ExpandLess,
                                contentDescription = "Collapse",
                                tint = colors.primaryContainer,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        // Bottom Pieces Carousel
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(14.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(bottomOptions) { item ->
                                val isActive = item.id == selectedBottomId
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .width(100.dp)
                                        .clickable { onSelectBottomPiece(item) }
                                ) {
                                    if (isActive) {
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(999.dp))
                                                .background(colors.primaryContainer)
                                                .padding(horizontal = 8.dp, vertical = 2.dp)
                                        ) {
                                            Text(
                                                text = "ACTIVE",
                                                color = colors.onPrimaryContainer,
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(4.dp))
                                    } else {
                                        Spacer(modifier = Modifier.height(18.dp))
                                    }

                                    Box(
                                        modifier = Modifier
                                            .size(80.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(colors.bg)
                                            .border(
                                                if (isActive) 2.dp else 1.dp,
                                                if (isActive) colors.primaryContainer else colors.divider,
                                                RoundedCornerShape(12.dp)
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        AsyncImage(
                                            model = ImageRequest.Builder(LocalContext.current)
                                                .data(item.imageUrl)
                                                .crossfade(true)
                                                .build(),
                                            contentDescription = item.name,
                                            contentScale = ContentScale.Fit,
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(4.dp)
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Text(
                                        text = item.name,
                                        color = colors.textPrimary,
                                        fontSize = 11.sp,
                                        maxLines = 1,
                                        textAlign = TextAlign.Center
                                    )

                                    Text(
                                        text = if (isActive) "Current" else if (item.wornCount > 10) "Paired often" else "Underworn",
                                        color = colors.textSecondary,
                                        fontSize = 10.sp
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Collapsed Kicks Row
                var isKicksExpanded by remember { mutableStateOf(false) }
                PieceSwapRow(
                    label = "KICKS",
                    itemName = "Salomon XT-6 (White/Silver)",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDhAt_n25BYZcZKTdNptTxsi_uLhZfF_B5CSXEC7P5Hv0jhLueO-u3knHKzt7Q_ojA9tNKff1bxqFWvnEQFyUqPnOglQWOhEYsnE0D_aEuIFoFHwQlJP9OsjQvCbvzZiYAMWRdJIyj9UQBGJ6uq1x1wkxLdWwI62UGCvJiqrPB-3ZYiJOsOK9tkzB_urGzlcJgvgZNBDl8R0SBqf8lPV5j4P-CjtyXuPKGFJnEayYRhOhiBtCx70s04",
                    isExpanded = isKicksExpanded,
                    onToggle = { isKicksExpanded = !isKicksExpanded }
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Curated Wildcard Button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .clip(CircleShape)
                        .background(colors.surfaceAlt)
                        .border(1.dp, colors.divider, CircleShape)
                        .clickable {
                            onTriggerWildcard()
                            onDismiss()
                        }
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Casino,
                        contentDescription = "Wildcard",
                        tint = colors.primaryContainer,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Curated Wildcard Fit",
                        color = colors.textPrimary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

@Composable
private fun PieceSwapRow(
    label: String,
    itemName: String,
    imageUrl: String,
    isExpanded: Boolean,
    onToggle: () -> Unit
) {
    val colors = TesseraThemeHelper.colors

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(colors.surfaceAlt)
            .border(1.dp, colors.divider, RoundedCornerShape(14.dp))
            .clickable(onClick = onToggle)
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(colors.bg)
                    .border(1.dp, colors.divider, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = itemName,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize().padding(2.dp)
                )
            }

            Column {
                Text(
                    text = label,
                    color = colors.textSecondary,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = itemName,
                    color = colors.textPrimary,
                    fontSize = 13.5.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1
                )
            }
        }

        Icon(
            imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = "Expand/Collapse",
            tint = colors.textSecondary,
            modifier = Modifier.size(20.dp)
        )
    }
}
