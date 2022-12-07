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
                onPrimary = Green10,
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
        2 -> {
            darkColorScheme(
                primary = Blue80,
                onPrimary = Blue10,
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
        3 -> {
            darkColorScheme(
                primary = Yellow80,
                onPrimary = Yellow10,
                primaryContainer = Yellow40,
                onPrimaryContainer = Yellow90,
                secondary = YellowGrey80,
                secondaryContainer = YellowGrey40,
                onSecondary = Yellow90,
                tertiary = YellowAlt80,
                tertiaryContainer = YellowAlt40,
                onTertiary = YellowAlt90,
                background = Color(0xFF201F1B),
                surface = Color(0xFF201F1B),
                surfaceVariant= YellowGrey10,
                onSurface = Color(0xFFFFFEFB),
                onBackground = Color(0xFFFFFEFB),
            )
        }
        4 -> {
            darkColorScheme(
                primary = Orange80,
                onPrimary = Orange10,
                primaryContainer = Orange40,
                onPrimaryContainer = Orange90,
                secondary = OrangeGrey80,
                secondaryContainer = OrangeGrey40,
                onSecondary = Orange90,
                tertiary = OrangeAlt80,
                tertiaryContainer = OrangeAlt40,
                onTertiary = OrangeAlt90,
                background = Color(0xFF201D1B),
                surface = Color(0xFF201D1B),
                surfaceVariant= OrangeGrey10,
                onSurface = Color(0xFFFFFDFB),
                onBackground = Color(0xFFFFFDFB),
            )
        }
        else -> {
            darkColorScheme(
                primary = Red80,
                onPrimary = Red10,
                primaryContainer = Red40,
                onPrimaryContainer = Red90,
                secondary = RedGrey80,
                secondaryContainer = RedGrey40,
                onSecondary = Red90,
                tertiary = RedAlt80,
                tertiaryContainer = RedAlt40,
                onTertiary = RedAlt90,
                background = Color(0xFF201B1B),
                surface = Color(0xFF201B1B),
                surfaceVariant= RedGrey10,
                onSurface = Color(0xFFFFFBFB),
                onBackground = Color(0xFFFFFBFB),
            )
        }
    }
    return colorScheme
}