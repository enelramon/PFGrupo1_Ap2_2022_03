package com.ucne.ticketsapp.ui.theme

import androidx.compose.material3.ColorScheme


fun customSystemColorScheme(darkTheme: Boolean, colorIndex: Int): ColorScheme {
    return if (darkTheme)
        customDarkColorScheme(colorIndex = colorIndex)
    else
        customLightColorsScheme(colorIndex = colorIndex)
}