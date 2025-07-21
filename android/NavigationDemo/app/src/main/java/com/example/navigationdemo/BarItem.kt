package com.example.navigationdemo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

data class BarItem(
  val title: String,
  val icon: ImageVector,
  val route: String
)

object NavBarItems {
  val BarItems = listOf(
    BarItem(
      title = "Home",
      icon = Icons.Filled.Home,
      route = "home"
    ),
    BarItem(
      title = "Contacts",
      icon = Icons.Filled.Face,
      route = "contacts"
    ),
    BarItem(
      title = "Favorites",
      icon = Icons.Filled.Favorite,
      route = "favorites"
    )
  )
}