package com.example.navigationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationdemo.screens.Home
import com.example.navigationdemo.screens.Profile
import com.example.navigationdemo.screens.Welcome
import com.example.navigationdemo.ui.theme.NavigationDemoTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      NavigationDemoTheme {
        Surface {
          MainScreen()
        }
      }
    }
  }
}

@Composable
fun MainScreen() {
  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = NavRoutes.Home.route) {
    composable(NavRoutes.Home.route) { Home(navController = navController) }
    composable(NavRoutes.Welcome.route + "/{userName}") { backStackEntry ->
      val userName = backStackEntry.arguments?.getString("userName")
      Welcome(navController = navController, userName)
    }
    composable(NavRoutes.Profile.route) { Profile() }
  }
}