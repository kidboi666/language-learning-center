package com.example.navigationdemo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

data class BarItem(
    val title: String, val icon: ImageVector, val route: String
)

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Practice", icon = Icons.Filled.Home, route = NavRoutes.Practice.route
        ),
        BarItem(
            title = "Composable", icon = Icons.Filled.Build, route = NavRoutes.Composable.route
        ),
    )
}