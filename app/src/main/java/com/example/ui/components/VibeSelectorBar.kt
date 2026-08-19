package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.TesseraThemeHelper

@Composable
fun VibeSelectorBar(
    vibes: List<String> = listOf("Drippy", "Attractive", "Serious", "Normal"),
    selectedVibe: String,
    onVibeSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(vibes) { vibe ->
            VibeChip(
                label = vibe,
                isSelected = vibe == selectedVibe,
                onClick = { onVibeSelected(vibe) }
            )
        }
    }
}

@Composable
fun VibeChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = TesseraThemeHelper.colors

    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) colors.primaryContainer else colors.surfaceAlt,
        label = "vibeChipBg"
    )
    val textColor by animateColorAsState(
        targetValue = if (isSelected) colors.onPrimaryContainer else colors.textSecondary,
        label = "vibeChipText"
    )
    val borderColor by animateColorAsState(
        targetValue = if (isSelected) colors.primaryContainer else colors.divider,
        label = "vibeChipBorder"
    )

    Box(
        modifier = modifier
            .testTag("vibe_chip_${label.lowercase()}")
            .then(
                if (isSelected) {
                    Modifier.shadow(
                        elevation = 6.dp,
                        shape = RoundedCornerShape(12.dp),
                        ambientColor = colors.primaryContainer.copy(alpha = 0.2f),
                        spotColor = colors.primaryContainer.copy(alpha = 0.4f)
                    )
                } else Modifier
            )
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 20.dp, vertical = 9.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = textColor,
            fontSize = 13.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
        )
    }
}
