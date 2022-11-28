package com.ucne.ticketsapp.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

fun customLightColorScheme(colorIndex: Int): ColorScheme {
    val colorScheme: ColorScheme = when (colorIndex) {
        0 -> {
            lightColorScheme(
                primary = Purple40,
                secondary = PurpleGrey40,
                tertiary = Pink40
            )
        }
        1 -> {
            lightColorScheme(
                primary = Green40,
                primaryContainer = Green80,
                onPrimaryContainer = Green10,
                secondary = GreenGrey40,
                secondaryContainer = GreenGrey80,
                onSecondary = Green10,
                tertiary = GreenAlt40,
                tertiaryContainer = GreenAlt80,
                onTertiary = GreenAlt10,
                background = Color(0xFFFBFFFB),
                surface = Color(0xFFFBFFFB),
                onBackground = Color(0xFF1B1F1C),
                onSurface = Color(0xFF1B1F1C),
            )
        }
        else -> {
            lightColorScheme(
                primary = Blue40,
                primaryContainer = Blue90,
                onPrimaryContainer = Blue10,
                secondary = BlueGrey40,
                secondaryContainer = BlueGrey90,
                onSecondary = Blue10,
                tertiary = BlueAlt40,
                tertiaryContainer = BlueAlt90,
                onTertiary = BlueAlt10,
                background = Color(0xFFFBFEFF),
                onBackground = Color(0xFF1B1C1F),
                surface = Color(0xFFFBFEFF),
                onSurface = Color(0xFF1B1C1F),
            )
        }
    }
    return colorScheme
}