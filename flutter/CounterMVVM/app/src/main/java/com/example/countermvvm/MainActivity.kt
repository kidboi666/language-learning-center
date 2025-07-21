package com.example.countermvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.countermvvm.ui.theme.CounterMVVMTheme

class MainActivity : ComponentActivity() {
  private val viewModel: CounterViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      CounterMVVMTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          TheCounterApp(viewModel = viewModel, modifier = Modifier.padding(innerPadding))
        }
      }
    }
  }
}

@Composable
fun TheCounterApp(viewModel: CounterViewModel, modifier: Modifier = Modifier) {

  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "Count: ${viewModel.count.value}",
      modifier = Modifier.padding(16.dp)
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row {
      Button(onClick = { viewModel.increment() }) {
        Text(text = "Increment")
      }
      Button(onClick = { viewModel.decrement() }) {
        Text(text = "Decrement")
      }
    }
  }

}