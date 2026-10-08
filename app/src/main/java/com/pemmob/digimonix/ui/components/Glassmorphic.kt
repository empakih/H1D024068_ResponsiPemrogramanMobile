package com.pemmob.digimonix.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun AtmosphericBackground(
    modifier: Modifier = Modifier,
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable BoxScope.() -> Unit
) {
    // Monochrome gradient palette (Hitam, Abu-abu, Putih)
    val gradientColors = remember(isDark) {
        if (isDark) {
            listOf(
                Color(0xFF000000), // Pure Black top
                Color(0xFF18181B), // Dark Charcoal
                Color(0xFF27272A), // Medium Slate Gray
                Color(0xFF09090B)  // Deep Charcoal bottom
            )
        } else {
            listOf(
                Color(0xFFFAFAFA), // Off-white top
                Color(0xFFE4E4E7), // Light Silver Gray
                Color(0xFFD4D4D8), // Mid Silver Gray
                Color(0xFFF4F4F5)  // Light Gray bottom
            )
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(colors = gradientColors)
            )
    ) {
        // Subtle ambient radial glow for glass refraction
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            if (isDark) Color(0xFF71717A).copy(alpha = 0.22f) else Color(0xFFA1A1AA).copy(alpha = 0.25f),
                            Color.Transparent
                        ),
                        center = Offset(300f, 350f),
                        radius = 900f
                    )
                )
        )

        content()
    }
}

@Composable
fun LiquidGlassSurface(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(16.dp),
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable BoxScope.() -> Unit
) {
    val glassFill = if (isDark) {
        Color(0x2EFFFFFF) // Translucent frosted glass fill on dark monochrome background
    } else {
        Color(0xCCFFFFFF) // Translucent frosted glass fill on light monochrome background
    }

    val glassBorderBrush = if (isDark) {
        Brush.linearGradient(
            colors = listOf(
                Color.White.copy(alpha = 0.60f),
                Color.White.copy(alpha = 0.15f),
                Color.White.copy(alpha = 0.40f)
            )
        )
    } else {
        Brush.linearGradient(
            colors = listOf(
                Color.White.copy(alpha = 0.95f),
                Color(0xFF71717A).copy(alpha = 0.35f),
                Color.White.copy(alpha = 0.75f)
            )
        )
    }

    Box(
        modifier = modifier
            .clip(shape)
            .background(glassFill)
            .border(width = 1.dp, brush = glassBorderBrush, shape = shape),
        content = content
    )
}
