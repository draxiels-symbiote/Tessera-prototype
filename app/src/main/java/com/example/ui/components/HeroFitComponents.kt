package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.local.OutfitEntity
import com.example.ui.theme.TesseraThemeHelper

@Composable
fun HeroGarmentCard(
    outfit: OutfitEntity,
    selectedPage: Int = 0,
    pageCount: Int = 4,
    onPageSelected: (Int) -> Unit = {},
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val colors = TesseraThemeHelper.colors

    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color.Black.copy(alpha = 0.3f),
                spotColor = Color.Black.copy(alpha = 0.5f)
            )
            .clip(RoundedCornerShape(28.dp))
            .background(colors.surface)
            .border(1.dp, colors.divider, RoundedCornerShape(28.dp))
            .clickable(onClick = onClick)
            .padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Asset Box (Dark Showcase Window)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
                    .shadow(
                        elevation = 12.dp,
                        shape = RoundedCornerShape(20.dp),
                        ambientColor = Color.Black,
                        spotColor = Color.Black.copy(alpha = 0.7f)
                    )
                    .clip(RoundedCornerShape(20.dp))
                    .background(colors.bg)
                    .border(1.dp, colors.divider, RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (outfit.imageUrl.isNotEmpty()) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(outfit.imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = outfit.name,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .scale(1.05f)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "Outfit image placeholder",
                        tint = colors.textSecondary.copy(alpha = 0.3f),
                        modifier = Modifier.size(64.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Metadata: WHOLE FIT capsule
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(colors.surfaceAlt)
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "WHOLE FIT",
                    color = colors.primaryContainer,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.5.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Outfit Title
            Text(
                text = outfit.name,
                color = colors.textPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("hero_outfit_title")
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Paired Shoes / Accessories
            Text(
                text = outfit.pairedWithText,
                color = colors.textSecondary,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Pagination Indicator
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in 0 until pageCount) {
                    val isActive = i == selectedPage
                    Box(
                        modifier = Modifier
                            .height(6.dp)
                            .width(if (isActive) 18.dp else 6.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(
                                if (isActive) colors.primaryContainer else colors.divider.copy(alpha = 0.4f)
                            )
                            .clickable { onPageSelected(i) }
                    )
                }
            }
        }
    }
}

@Composable
fun HairMatchSlot(
    stylingText: String = "Today's styling: half up, low and loose",
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = TesseraThemeHelper.colors

    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(colors.surfaceAlt)
            .border(1.dp, colors.divider, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(16.dp)
            .testTag("hair_match_slot"),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Bolt,
                    contentDescription = "Hair match",
                    tint = colors.primaryContainer,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "HAIR MATCH",
                    color = colors.textSecondary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.sp
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stylingText,
                color = colors.textPrimary,
                fontSize = 13.5.sp,
                fontWeight = FontWeight.Normal
            )
        }

        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowForwardIos,
            contentDescription = "Navigate to Hair Setup",
            tint = colors.textSecondary,
            modifier = Modifier.size(14.dp)
        )
    }
}

@Composable
fun LivingCaptionBox(
    promptText: String = "Ask Tessera to change something",
    onAdjustClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = TesseraThemeHelper.colors

    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(colors.surfaceAlt)
            .border(1.dp, colors.divider, RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Chat,
                contentDescription = "Prompt",
                tint = colors.textSecondary,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = promptText,
                color = colors.textSecondary,
                fontSize = 13.5.sp
            )
        }

        // Adjust Button
        Row(
            modifier = Modifier
                .testTag("adjust_button")
                .clip(RoundedCornerShape(999.dp))
                .background(colors.surface)
                .border(1.dp, colors.divider, RoundedCornerShape(999.dp))
                .clickable(onClick = onAdjustClick)
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Tune,
                contentDescription = "Adjust",
                tint = colors.primaryContainer,
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = "Adjust",
                color = colors.primaryContainer,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun LockItInButton(
    isLocked: Boolean,
    onLockClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = TesseraThemeHelper.colors
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(targetValue = if (isPressed) 0.96f else 1f, label = "buttonScale")

    Box(
        modifier = modifier
            .scale(scale)
            .fillMaxWidth()
            .height(56.dp)
            .shadow(
                elevation = 16.dp,
                shape = CircleShape,
                ambientColor = colors.primaryContainer.copy(alpha = 0.4f),
                spotColor = colors.primaryContainer.copy(alpha = 0.7f)
            )
            .clip(CircleShape)
            .background(colors.primaryGradient)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onLockClick
            )
            .testTag("lock_it_in_button"),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (isLocked) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Locked",
                    tint = colors.onPrimaryContainer,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "LOCKED IN",
                    color = colors.onPrimaryContainer,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                )
            } else {
                Text(
                    text = "LOCK IT IN",
                    color = colors.onPrimaryContainer,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                )
            }
        }
    }
}
