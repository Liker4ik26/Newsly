package com.network.newsly.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    onPrimary = BostonBlue,
    background = Casper,
    onSecondary = Color.Black,
    secondary = Color.White,
    surfaceTint = Color.Gray,
    tertiary = Color.Black,
    surface = Firefly,
    primary = Color.White,
    secondaryContainer = SnowWhite,
    error = DarkRed
)

private val LightColorScheme = lightColorScheme(
    onPrimary = BostonBlue,
    background = WhiteSmoke,
    secondary = Color.Black,
    onSecondary = Color.White,
    surfaceTint = Color.Gray,
    tertiary = Color.Black,
    surface = Color.White,
    primary = ChineseBlack,
    secondaryContainer = BlackMist,
    error = SunsetOrange
)

@Composable
fun NewslyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val view = LocalView.current
    (view.context as? Activity)?.window
        ?: throw Exception("Not in an activity - unable to get Window reference")
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    systemUiController.setSystemBarsColor(
        color = Color.Transparent,
        darkIcons = !darkTheme,
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}