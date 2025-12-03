package com.pful.aitplacements.ui.theme

import android.app.Activity
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
object AppTheme {
    // Light Theme Colors
    val Primary = Color(0xFF6366F1)
    val PrimaryVariant = Color(0xFF4F46E5)
    val Secondary = Color(0xFF8B5CF6)
    val Background = Color(0xFFF8FAFC)
    val Surface = Color(0xFFFFFFFF)
    val SurfaceVariant = Color(0xFFF1F5F9)
    val OnPrimary = Color.White
    val OnBackground = Color(0xFF1E293B)
    val OnSurface = Color(0xFF334155)
    val TextSecondary = Color(0xFF64748B)
    val Error = Color(0xFFEF4444)
    val Success = Color(0xFF10B981)
    val CardGradientStart = Color(0xFFFAFAFA)
    val CardGradientEnd = Color(0xFFFFFFFF)

    // Dark Theme Colors
    val DarkPrimary = Color(0xFF818CF8)
    val DarkPrimaryVariant = Color(0xFF6366F1)
    val DarkSecondary = Color(0xFFA78BFA)
    val DarkBackground = Color(0xFF0F172A)
    val DarkSurface = Color(0xFF1E293B)
    val DarkSurfaceVariant = Color(0xFF334155)
    val DarkOnPrimary = Color(0xFF0F172A)
    val DarkOnBackground = Color(0xFFF1F5F9)
    val DarkOnSurface = Color(0xFFE2E8F0)
    val DarkTextSecondary = Color(0xFF94A3B8)
    val DarkError = Color(0xFFF87171)
    val DarkSuccess = Color(0xFF34D399)
    val DarkCardGradientStart = Color(0xFF1E293B)
    val DarkCardGradientEnd = Color(0xFF334155)
}
private val DarkColorScheme = darkColorScheme(
    primary = AppTheme.DarkPrimary,
    primaryContainer = AppTheme.DarkPrimaryVariant,
    secondary = AppTheme.DarkSecondary,
    background = AppTheme.DarkBackground,
    surface = AppTheme.DarkSurface,
    surfaceVariant = AppTheme.DarkSurfaceVariant,
    onPrimary = AppTheme.DarkOnPrimary,
    onBackground = AppTheme.DarkOnBackground,
    onSurface = AppTheme.DarkOnSurface,
    error = AppTheme.DarkError
)

private val LightColorScheme = lightColorScheme(
    primary = AppTheme.Primary,
    primaryContainer = AppTheme.PrimaryVariant,
    secondary = AppTheme.Secondary,
    background = AppTheme.Background,
    surface = AppTheme.Surface,
    surfaceVariant = AppTheme.SurfaceVariant,
    onPrimary = AppTheme.OnPrimary,
    onBackground = AppTheme.OnBackground,
    onSurface = AppTheme.OnSurface,
    error = AppTheme.Error
)

@Composable
fun AITPlacementsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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