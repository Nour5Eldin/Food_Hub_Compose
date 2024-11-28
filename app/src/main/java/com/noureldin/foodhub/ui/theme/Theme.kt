package com.noureldin.foodhub.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    background = backgroundDark,
    onBackground = onBackgroundDark, // Text color for dark mode
    surface = surfaceDark,
    onSurface = onSurfaceDark, // Text color on surface for dark mode
    secondary = secondaryDark,
    onSecondary = onSecondaryDark
)

private val LightColorScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    background = backgroundLight,
    onBackground = onBackgroundLight, // Text color for light mode
    surface = surfaceLight,
    onSurface = onSurfaceLight, // Text color on surface for light mode
    secondary = secondaryLight,
    onSecondary = onSecondaryLight
)

@Composable
fun FoodHubTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Enable dynamic color if needed
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

    val view = LocalView.current
    if (!view.isInEditMode) {
        LaunchedEffect(colorScheme) {
            (view.context as? Activity)?.window?.apply {
                statusBarColor = colorScheme.primary.toArgb()
                WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}