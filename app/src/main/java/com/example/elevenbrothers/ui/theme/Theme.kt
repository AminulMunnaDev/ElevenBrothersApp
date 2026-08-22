package com.example.elevenbrothers.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = BrandBlueLight,
    onPrimary = BrandNavyDark,
    primaryContainer = BrandNavy,
    onPrimaryContainer = BrandBlueLight,
    secondary = BrandGold,
    onSecondary = BrandNavyDark,
    secondaryContainer = BrandGoldDark,
    onSecondaryContainer = BrandGoldLight,
    background = DarkBackground,
    onBackground = DarkForeground,
    surface = DarkBackground,
    onSurface = DarkForeground
)

private val LightColorScheme = lightColorScheme(
    primary = BrandBlue,
    onPrimary = Color.White,
    primaryContainer = BrandNavy,
    onPrimaryContainer = Color.White,
    secondary = BrandGold,
    onSecondary = BrandNavyDark,
    secondaryContainer = BrandGoldLight,
    onSecondaryContainer = BrandGoldDark,
    background = Background,
    onBackground = Foreground,
    surface = Background,
    onSurface = Foreground
)

@Composable
fun ElevenBrothersTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Set dynamicColor to false by default if you want your brand palette
    // to always take priority over Material You wallpaper colors on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}