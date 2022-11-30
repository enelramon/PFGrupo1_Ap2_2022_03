package com.ucne.ticketsapp.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

fun customDarkColorScheme(colorIndex: Int): ColorScheme {
    val colorScheme: ColorScheme = when (colorIndex) {
        0 -> {
            darkColorScheme(
                primary = Purple80,
                secondary = PurpleGrey80,
                tertiary = Pink80
            )
        }
        1 -> {
            darkColorScheme(
                primary = Green80,
                primaryContainer = Green40,
                onPrimaryContainer = Green90,
                secondary = GreenGrey80,
                secondaryContainer = GreenGrey40,
                onSecondary = Green90,
                tertiary = GreenAlt80,
                tertiaryContainer = GreenAlt40,
                onTertiary = GreenAlt90,
                background = Color(0xFF1B1F1C),
                surface = Color(0xFF1B1F1C),
                surfaceVariant= GreenGrey10,
                onBackground = Color(0xFFFBFFFB),
                onSurface = Color(0xFFFBFFFB),
            )
        }
        else -> {
            darkColorScheme(
                primary = Blue80,
                primaryContainer = Blue40,
                onPrimaryContainer = Blue90,
                secondary = BlueGrey80,
                secondaryContainer = BlueGrey40,
                onSecondary = Blue90,
                tertiary = BlueAlt80,
                tertiaryContainer = BlueAlt40,
                onTertiary = BlueAlt90,
                background = Color(0xFF1B1C20),
                surface = Color(0xFF1B1C20),
                surfaceVariant= BlueGrey10,
                onSurface = Color(0xFFFBFEFF),
                onBackground = Color(0xFFFBFEFF),
            )
        }
    }
    return colorScheme
}