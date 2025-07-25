package com.example.bottomnavigationdemo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person

object NavBarItems {
  val BarItems = listOf(
    BarItem(
      title = "Home",
      image = Icons.Filled.Home,
      route = "home"
    ),
    BarItem(
      title = "Contacts",
      image = Icons.Filled.Face,
      route = "contacts"
    ),
    BarItem(
      title = "Favorites",
      image = Icons.Filled.Favorite,
      route = "favorites"
    )
  )
}