package com.example.onboarding.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

enum class ColorTheme(val seedColor: Color) {
  GREEN(Color(0xFF3FB580)),
  BLUE(Color(0xFF2A9DEB)),
  YELLOW(Color(0xFFFED23F)),
  ORANGE(Color(0xFFF5964B)),
  BLACK(Color(0xFF131313)),
//  'var-orange': '#F5964B',
}

@Composable
fun OnboardingTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  colorTheme: ColorTheme = ColorTheme.GREEN,
  // Dynamic color is available on Android 12+
  content: @Composable () -> Unit
) {
  val colorScheme = when {
    darkTheme -> darkColorScheme(primary = colorTheme.seedColor)
    else -> lightColorScheme(primary = colorTheme.seedColor)
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}