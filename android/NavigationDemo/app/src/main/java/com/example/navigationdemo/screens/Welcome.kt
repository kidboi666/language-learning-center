package com.example.navigationdemo.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.navigationdemo.NavRoutes

@Composable
fun Welcome(navController: NavHostController, userName: String?) {

  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Text(text = "Welcome, $userName")
      Spacer(modifier = Modifier.size(30.dp))
      Button(onClick = { navController.navigate(NavRoutes.Profile.route) }) {
        Text(text = "Set up your profile")
      }
    }
  }
}