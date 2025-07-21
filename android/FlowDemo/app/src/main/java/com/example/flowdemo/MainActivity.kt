package com.example.flowdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flowdemo.ui.theme.FlowDemoTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlin.system.measureTimeMillis

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      FlowDemoTheme {
        Surface {
          ScreenSetup()
        }
      }
    }
  }
}

@Composable
fun ScreenSetup(viewModel: DemoViewModel = viewModel()) {
  MainScreen(viewModel.newFlow)
}

@Composable
fun MainScreen(flow: Flow<String>) {
//  val count by flow.collectAsState(initial = 0)
  var count by remember { mutableStateOf("Current value =") }

  LaunchedEffect(Unit) {
    val elapsedTime = measureTimeMillis {
      flow.buffer().collect {
        count = it
        delay(1000)
      }
    }

    count = "Duration = $elapsedTime"
  }
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(text = "Count: $count")
  }
}

class DemoViewModel : ViewModel() {
  val myFlow: Flow<Int> = flow {
    for (i in 1..10) {
      emit(i)
      delay(2000)
    }
  }

  val newFlow = myFlow.map {
    "Current value: $it"
  }

  val transformedFlow = myFlow.transform {
    emit("Value: $it")
    delay(1000)
    val doubled = it * 2
    emit("Value doubled = $doubled")
  }
}

@Preview
@Composable
fun DefaultPreview() {
  FlowDemoTheme {
    Surface {
      ScreenSetup(viewModel())
    }
  }
}