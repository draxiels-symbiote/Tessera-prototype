package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.TesseraThemeHelper

@Composable
fun TesseraTopAppBar(
    streakDays: Int,
    weatherText: String,
    modifier: Modifier = Modifier
) {
    val colors = TesseraThemeHelper.colors

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ember Streak Badge
        Row(
            modifier = Modifier
                .testTag("ember_streak_badge")
                .shadow(
                    elevation = 4.dp,
                    shape = CircleShape,
                    ambientColor = colors.primaryContainer.copy(alpha = 0.3f),
                    spotColor = colors.primaryContainer.copy(alpha = 0.5f)
                )
                .clip(CircleShape)
                .background(colors.surfaceAlt)
                .border(1.dp, colors.primaryContainer.copy(alpha = 0.3f), CircleShape)
                .padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.LocalFireDepartment,
                contentDescription = "Streak flame",
                tint = colors.primaryContainer,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = streakDays.toString(),
                color = colors.primaryContainer,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
        }

        // Center Brand Wordmark
        Text(
            text = "TESSERA",
            color = colors.textPrimary,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 3.sp,
            modifier = Modifier.testTag("brand_title")
        )

        // Weather Location Tag
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.testTag("weather_chip")
        ) {
            Icon(
                imageVector = Icons.Outlined.WbSunny,
                contentDescription = "Weather",
                tint = colors.textSecondary.copy(alpha = 0.8f),
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = weatherText,
                color = colors.textSecondary,
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}
