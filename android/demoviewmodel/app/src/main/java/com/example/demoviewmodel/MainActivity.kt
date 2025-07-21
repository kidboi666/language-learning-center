package com.example.demoviewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.demoviewmodel.ui.theme.DemoviewmodelTheme
import com.example.demoviewmodel.view.MainScreen
import com.example.demoviewmodel.viewmodel.DemoViewModel

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      DemoviewmodelTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
          Box(modifier = Modifier.padding(padding)) {
            MainScreen(viewModel = DemoViewModel())
          }
        }
      }
    }
  }
}
