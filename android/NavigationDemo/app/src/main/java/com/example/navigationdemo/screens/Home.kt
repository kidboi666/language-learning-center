package com.example.navigationdemo.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.navigationdemo.NavRoutes

@Composable
fun Home(navController: NavHostController) {
  var userName by remember { mutableStateOf("") }
  val onUserNameChange = { text: String -> userName = text }

  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      CustomTextField(
        title = "Enter Your Name",
        value = userName,
        onValueChange = onUserNameChange
      )

      Spacer(modifier = Modifier.size(30.dp))

      Button(onClick = {
        navController.navigate(NavRoutes.Welcome.route + "/$userName")
      }) {
        Text(text = "Register")
      }
    }
  }
}

@Composable
fun CustomTextField(title: String, value: String, onValueChange: (String) -> Unit) {
  OutlinedTextField(
    value = value,
    onValueChange = { onValueChange(it) },
    label = { Text(title) },
    singleLine = true,
    modifier = Modifier.padding(10.dp),
    textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp)
  )
}
