package com.pemmob.digimonix.ui.theme

import androidx.compose.ui.graphics.Color

// Honest, Restrained Palette (1 Primary, 1 Accent, neutrals)
// Primary: Slate Black / Pure White
// Accent: Deep Blue
val Slate900 = Color(0xFF0F172A)
val Slate800 = Color(0xFF1E293B)
val Slate100 = Color(0xFFF1F5F9)
val Slate50 = Color(0xFFF8FAFC)

val AccentBlue = Color(0xFF2563EB)
val AccentBlueDark = Color(0xFF3B82F6)

// Light Theme
val PrimaryLight = Slate900
val OnPrimaryLight = Color.White
val PrimaryContainerLight = Slate100
val OnPrimaryContainerLight = Slate900

val SecondaryLight = Slate800
val OnSecondaryLight = Color.White
val SecondaryContainerLight = Slate50
val OnSecondaryContainerLight = Slate900

val BackgroundLight = Color.White
val OnBackgroundLight = Slate900
val SurfaceLight = Color.White
val OnSurfaceLight = Slate900
val SurfaceVariantLight = Slate100
val OnSurfaceVariantLight = Slate800

val OutlineLight = Color(0xFFE2E8F0) // Sharp 1px border color

// Dark Theme
val PrimaryDark = Color.White
val OnPrimaryDark = Slate900
val PrimaryContainerDark = Slate800
val OnPrimaryContainerDark = Color.White

val SecondaryDark = Slate100
val OnSecondaryDark = Slate900
val SecondaryContainerDark = Slate900
val OnSecondaryContainerDark = Color.White

val BackgroundDark = Slate900
val OnBackgroundDark = Color.White
val SurfaceDark = Slate900
val OnSurfaceDark = Color.White
val SurfaceVariantDark = Slate800
val OnSurfaceVariantDark = Slate100

val OutlineDark = Slate800 // Sharp 1px border color

// Digimon Attribute Badge Colors (Functional, semantic meaning only)
val VaccineColor = Color(0xFF16A34A) // Green
val DataColor = Color(0xFF2563EB)    // Blue
val VirusColor = Color(0xFFDC2626)   // Red
val FreeColor = Slate800
val UnknownColor = Slate800