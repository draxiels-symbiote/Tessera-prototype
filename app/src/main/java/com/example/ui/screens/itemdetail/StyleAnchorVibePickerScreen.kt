package com.example.ui.screens.itemdetail

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import com.example.ui.viewmodel.TesseraViewModel

data class StyleAnchorOption(
    val vibe: String,
    val title: String,
    val topAndBottom: String,
    val shoes: String,
    val hair: String,
    val imageUrl: String
)

val anchorOptions = listOf(
    StyleAnchorOption(
        vibe = "Serious",
        title = "Boardroom Ready",
        topAndBottom = "Crisp Oxford Shirt & Tailored Charcoal Trousers",
        shoes = "Black Leather Loafer",
        hair = "Neat part, pulled back",
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDMfi4qk0x-QSFN-6gje0rvLDIKC6Qrxwak-PdivurOinpvEkCysg7ccAliCay9VEmHYklH5g4vgdtdnVVguea3wKIf-bo9-9tsFaO13UHBSk8akvsTsAvpbFusHBCUnioBvI8NhyYVOLwbaZDzKRIHtmXXJZIcoPXqgeOPfAMVRQ_00_BFwMDH2Ru5UNSea1lQ8nO0nrPbaE1R_r4UNkhI0o4YoZhupfRMgTff74f4UJ3nqxe6B2kU"
    ),
    StyleAnchorOption(
        vibe = "Attractive",
        title = "Elevated Evening",
        topAndBottom = "Silk Knit Polo & Dark Wash Denim",
        shoes = "Black Chelsea Boot",
        hair = "Loose waves, side swept",
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDyVhWQdOMgwJWfOesf6ZxversFArC1Xm3YpMvZPuS83ui1FNALZ9Y6x1dQ7sg6hb2mMrIeQwPSLQOPbDQdPwcT4jgvw6nO7xaqMvBQxl8Si3NVrSjOemE7744nVb6IbWTrO9ParoSKxmgqIa229j7tOfEWeV9sRPAdnLzx1Y7s_AwTXB7NhB_AbCbjpqfjRZDNCY2Q5j1mteVmqY3RGMxlO-t9Em3JJrATlUnAqmkeYQSicXakRFC-"
    ),
    StyleAnchorOption(
        vibe = "Drippy",
        title = "Smart Street",
        topAndBottom = "Oversized Tee & Cargo Trouser",
        shoes = "Minimalist White Sneaker",
        hair = "Half up, low and loose",
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBDVOaa0o4VUqz5X3DGC6Dc6aPGnWtHWlx6SKyIM3rXnoyN_yEMXNxZKwwwBZE6Gq0W-oN8YeeCKLIF5yuZOOpPI6lBK8OFxCWNggU6khUIUwkE8ahrwKZU5BEc2O71zv4mybxMGFMXwhpcQNT2SWMajkW_4-RQraaqVZrm3d3gKDNYgGI6WkEx7MWIKM-9txlF-ci9dh31kxLRvQNutKWMn3fyKfliVPQkP9_WCVCqRhvY072PGZTN"
    )
)

@Composable
fun StyleAnchorVibePickerScreen(
    itemId: Long,
    viewModel: TesseraViewModel,
    onBack: () -> Unit,
    onUseFit: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = TesseraThemeHelper.colors
    var selectedVibe by remember { mutableStateOf("Serious") }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = colors.textPrimary
                    )
                }

                Column {
                    Text(
                        text = "ITEM DETAIL",
                        color = colors.textSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Style with the blazer",
                        color = colors.textPrimary,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .shadow(12.dp, CircleShape)
                        .clip(CircleShape)
                        .background(colors.primaryGradient)
                        .clickable {
                            viewModel.setVibe(selectedVibe)
                            onUseFit()
                        }
                        .testTag("use_fit_today_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Use this fit today 🔒",
                        color = colors.onPrimaryContainer,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
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
                text = "3 ways to wear it today",
                color = colors.textPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Tessera built these around your charcoal tailored blazer and current weather.",
                color = colors.textMuted,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(anchorOptions) { option ->
                    val isSelected = option.vibe == selectedVibe
                    AnchorVibeCard(
                        option = option,
                        isSelected = isSelected,
                        onClick = { selectedVibe = option.vibe }
                    )
                }
            }
        }
    }
}

@Composable
private fun AnchorVibeCard(
    option: StyleAnchorOption,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = TesseraThemeHelper.colors

    val borderColor by animateColorAsState(
        targetValue = if (isSelected) colors.primaryContainer else colors.divider,
        label = "anchorBorder"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .testTag("anchor_vibe_${option.vibe.lowercase()}")
            .then(
                if (isSelected) {
                    Modifier.shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(24.dp),
                        ambientColor = colors.primaryContainer.copy(alpha = 0.2f),
                        spotColor = colors.primaryContainer.copy(alpha = 0.4f)
                    )
                } else Modifier
            )
            .clip(RoundedCornerShape(24.dp))
            .background(if (isSelected) colors.surfaceActive else colors.surface)
            .border(if (isSelected) 1.5.dp else 1.dp, borderColor, RoundedCornerShape(24.dp))
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image Preview Box
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(colors.bg)
                    .border(1.dp, colors.divider, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(option.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = option.title,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize().padding(4.dp)
                )
            }

            // Info Column
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (isSelected) colors.primaryContainer else colors.surfaceAlt)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = option.vibe.uppercase(),
                            color = if (isSelected) colors.onPrimaryContainer else colors.textSecondary,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    }

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

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = option.title,
                    color = colors.textPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = option.topAndBottom,
                    color = colors.textSecondary,
                    fontSize = 12.sp,
                    maxLines = 1
                )

                Text(
                    text = "Shoes: ${option.shoes}",
                    color = colors.textMuted,
                    fontSize = 11.sp,
                    maxLines = 1
                )
            }
        }
    }
}
