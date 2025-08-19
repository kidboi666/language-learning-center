package com.example.flowdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flowdemo.ui.theme.FlowDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlowDemoTheme {
                Surface {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: DemoViewModel = viewModel()) {
    val count by viewModel.stateFlow.collectAsState()
    val sharedCount by viewModel.sharedFlow.collectAsState(initial = 0)
    val subCount by viewModel.subCount.collectAsState(initial = 0)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Count: $count")
        Text(text = "Shared Count: $sharedCount")
        Text(text = "Subscriber Count: $subCount")
        Button(onClick = { viewModel.increaseValue() }) { Text("Plus Count State Flow") }
        Button(onClick = { viewModel.startSharedFlow() }) { Text("Start Shared Flow") }
    }
}
