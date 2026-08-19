package com.example.ui.screens.additem

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ui.theme.TesseraThemeHelper
import kotlinx.coroutines.delay

@Composable
fun AddItemProcessingScreen(
    imageUri: String,
    onProcessingFinished: (name: String, category: String, vibe: String, imageUrl: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = TesseraThemeHelper.colors
    var currentStep by remember { mutableIntStateOf(1) }

    val rawBlazerUrl = "https://lh3.googleusercontent.com/aida/AP1WRLuIlSzgCMR-pCDEXPFPqo74iy7TGawD8eQBcUgm3gwm_LJvJTXDMqIgUT2CCaIG1fTv_OqPxGAn1NX8HqiSQfwcemRNlXGbBFlyezOVjoBedCKcNh1ULcLsIJIoWCrqbWmnzOkKz0pdeWr8BdrdIRFT4GdWC-Djj_ET9Yi4rU8_9hdjWRvd0mdVK0yUG7dScaV227JoT2JmWzBWvRdkZ1mU2jwGeQpdm6UBwHGawbViB9eRplsyrdCCtxA"
    val cutoutBlazerUrl = "https://lh3.googleusercontent.com/aida/AP1WRLs00ca9OICSXAHqVtbylUHmPlCNVC_tJPS6WhFOGx3h99T4ksBJ0_p1ehbdgzOSXrpyGaFi8-YqLuLmAt5skiMghm1NARMLn_zYehFt8wm8id4tnfoHwX0ybq-QePryC42-xeDm8PrHO6linfFJWw6jLo9E4WG8_nmldaHo__asUMNyrIP-S0RwwxzMPuJQfOMDlDbgnYUH7rT1Zp0cyDyaV6wKbKXsUEnFsA7I9vI9aD77Hy86TBoKlfo"

    // Animation for laser scan
    val infiniteTransition = rememberInfiniteTransition(label = "laserScan")
    val laserPosition by infiniteTransition.animateFloat(
        initialValue = 0.05f,
        targetValue = 0.95f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "laserPosition"
    )

    LaunchedEffect(Unit) {
        delay(700)
        currentStep = 2
        delay(800)
        currentStep = 3
        delay(900)
        onProcessingFinished(
            "Charcoal Tailored Blazer",
            "Outerwear",
            "Serious",
            cutoutBlazerUrl
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.bg)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Header
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                text = "TESSERA AI ENGINE",
                color = colors.primaryContainer,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 1.5.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "ISOLATING PIECE",
                color = colors.textPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Showcase container with animated laser scan
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .shadow(16.dp, RoundedCornerShape(28.dp))
                .clip(RoundedCornerShape(28.dp))
                .background(colors.surface)
                .border(1.dp, colors.primaryContainer.copy(alpha = 0.4f), RoundedCornerShape(28.dp)),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(if (currentStep >= 2) cutoutBlazerUrl else rawBlazerUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Processing Garment",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            )

            // Scanning laser line
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .align(Alignment.TopCenter)
                    .padding(top = (300 * laserPosition).dp)
                    .background(colors.primaryGradient)
                    .shadow(8.dp, CircleShape, ambientColor = colors.primaryContainer, spotColor = colors.primaryContainer)
            )
        }

        // Live Step Checklist
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(colors.surfaceAlt)
                .border(1.dp, colors.divider, RoundedCornerShape(20.dp))
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            ProcessingStepRow(
                label = "Background removed cleanly",
                isComplete = currentStep >= 1,
                isInProgress = false
            )
            ProcessingStepRow(
                label = "Analyzing silhouette & material",
                isComplete = currentStep >= 2,
                isInProgress = currentStep == 1
            )
            ProcessingStepRow(
                label = "Generating tags & category matches",
                isComplete = currentStep >= 3,
                isInProgress = currentStep == 2
            )
        }

        // Bottom Progress Bar
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            LinearProgressIndicator(
                progress = {
                    when (currentStep) {
                        1 -> 0.33f
                        2 -> 0.66f
                        else -> 1f
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp)),
                color = colors.primaryContainer,
                trackColor = colors.surfaceAlt,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Almost ready...",
                color = colors.textSecondary,
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

@Composable
private fun ProcessingStepRow(
    label: String,
    isComplete: Boolean,
    isInProgress: Boolean
) {
    val colors = TesseraThemeHelper.colors

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (isComplete) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF2ED9A0)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    tint = Color(0xFF003826),
                    modifier = Modifier.size(12.dp)
                )
            }
        } else if (isInProgress) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                strokeWidth = 2.dp,
                color = colors.primaryContainer
            )
        } else {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(colors.surface)
                    .border(1.dp, colors.divider, CircleShape)
            )
        }

        Text(
            text = label,
            color = if (isComplete) colors.textPrimary else colors.textSecondary,
            fontSize = 13.5.sp,
            fontWeight = if (isComplete) FontWeight.Medium else FontWeight.Normal
        )
    }
}
