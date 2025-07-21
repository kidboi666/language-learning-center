package com.example.bottomnavigationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationdemo.screens.Contacts
import com.example.bottomnavigationdemo.screens.Favorites
import com.example.bottomnavigationdemo.screens.Home
import com.example.bottomnavigationdemo.ui.theme.BottomNavigationDemoTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      BottomNavigationDemoTheme {
          MainScreen()
      }
    }
  }
}


@Composable
fun MainScreen() {
  val navController = rememberNavController()

  Scaffold(
    bottomBar = { BottomNavigationBar(navController = navController) },
    content = { padding ->
      Box(modifier = Modifier.padding(padding)) {
        NavigationHost(navController = navController)
      }
    }
  )
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {

  NavigationBar {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    NavBarItems.BarItems.forEach { navItem ->

      NavigationBarItem(
        selected = currentRoute == navItem.route,
        onClick = {
          navController.navigate(navItem.route) {
            popUpTo(navController.graph.findStartDestination().id) {
              saveState = true
            }
            launchSingleTop = true
            restoreState = true
          }
        },

        icon = {
          Icon(imageVector = navItem.image,
            contentDescription = navItem.title)
        },
        label = {
          Text(text = navItem.title)
        },
      )
    }
  }
}
@Composable
fun NavigationHost( navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = NavRoutes.Home.route
  ) {
    composable(NavRoutes.Home.route) {
      Home()
    }
    composable(NavRoutes.Contacts.route) {
      Contacts()
    }
    composable(NavRoutes.Favorites.route) {
      Favorites()
    }

  }
}