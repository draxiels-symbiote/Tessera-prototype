package com.example.ui.screens.additem

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.TesseraThemeHelper

@Composable
fun AddItemCaptureScreen(
    onCaptureComplete: (String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = TesseraThemeHelper.colors
    var flashEnabled by remember { mutableStateOf(false) }

    val defaultBlazerUrl = "https://lh3.googleusercontent.com/aida/AP1WRLuIlSzgCMR-pCDEXPFPqo74iy7TGawD8eQBcUgm3gwm_LJvJTXDMqIgUT2CCaIG1fTv_OqPxGAn1NX8HqiSQfwcemRNlXGbBFlyezOVjoBedCKcNh1ULcLsIJIoWCrqbWmnzOkKz0pdeWr8BdrdIRFT4GdWC-Djj_ET9Yi4rU8_9hdjWRvd0mdVK0yUG7dScaV227JoT2JmWzBWvRdkZ1mU2jwGeQpdm6UBwHGawbViB9eRplsyrdCCtxA"

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Main Viewfinder Canvas
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp, bottom = 140.dp, start = 20.dp, end = 20.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(Color(0xFF141216))
                .border(2.dp, colors.divider, RoundedCornerShape(32.dp)),
            contentAlignment = Alignment.Center
        ) {
            // Hanger Guide Outline
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(180.dp)
                        .clip(CircleShape)
                        .background(colors.primaryContainer.copy(alpha = 0.05f))
                        .border(1.dp, colors.primaryContainer.copy(alpha = 0.2f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Checkroom,
                        contentDescription = "Hanger guide",
                        tint = colors.primaryContainer.copy(alpha = 0.4f),
                        modifier = Modifier.size(90.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Center piece on hanger or flat surface",
                    color = colors.textSecondary,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center
                )
            }

            // Viewfinder Corner Brackets
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                // Top-Left Corner
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .size(24.dp)
                        .border(
                            width = 2.dp,
                            color = colors.primaryContainer,
                            shape = RoundedCornerShape(topStart = 8.dp)
                        )
                )
                // Top-Right Corner
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(24.dp)
                        .border(
                            width = 2.dp,
                            color = colors.primaryContainer,
                            shape = RoundedCornerShape(topEnd = 8.dp)
                        )
                )
                // Bottom-Left Corner
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .size(24.dp)
                        .border(
                            width = 2.dp,
                            color = colors.primaryContainer,
                            shape = RoundedCornerShape(bottomStart = 8.dp)
                        )
                )
                // Bottom-Right Corner
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(24.dp)
                        .border(
                            width = 2.dp,
                            color = colors.primaryContainer,
                            shape = RoundedCornerShape(bottomEnd = 8.dp)
                        )
                )
            }
        }

        // Top Navigation Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.6f))
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close",
                    tint = Color.White
                )
            }

            // Live badge
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(Color.Black.copy(alpha = 0.6f))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF2ED9A0))
                )
                Text(
                    text = "LIVE AI CUTOUT",
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
            }

            IconButton(
                onClick = { flashEnabled = !flashEnabled },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(if (flashEnabled) colors.primaryContainer else Color.Black.copy(alpha = 0.6f))
            ) {
                Icon(
                    imageVector = Icons.Filled.FlashOn,
                    contentDescription = "Flash",
                    tint = if (flashEnabled) colors.onPrimaryContainer else Color.White
                )
            }
        }

        // Bottom Controls Bar
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Photo gallery picker
                IconButton(
                    onClick = { onCaptureComplete(defaultBlazerUrl) },
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(colors.surfaceAlt)
                        .border(1.dp, colors.divider, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Filled.PhotoLibrary,
                        contentDescription = "Gallery",
                        tint = colors.textPrimary
                    )
                }

                // Shutter Button
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .shadow(16.dp, CircleShape, ambientColor = colors.primaryContainer, spotColor = colors.primaryContainer)
                        .clip(CircleShape)
                        .background(Color.White)
                        .border(4.dp, colors.primaryContainer, CircleShape)
                        .clickable { onCaptureComplete(defaultBlazerUrl) }
                        .testTag("shutter_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    )
                }

                // Spacer balance
                Box(modifier = Modifier.size(50.dp))
            }
        }
    }
}
