package com.example.navigationdemo.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

val LocalColor = staticCompositionLocalOf { Color.Green }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompositionLocal() {
  val color = if (isSystemInDarkTheme()) {
    Color.Green
  } else {
    Color.Red
  }

  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Text("CompositionLocal Screen", style = MaterialTheme.typography.headlineMedium)
    Compo2()
    CompositionLocalProvider(LocalColor provides color) {
      Compo3()
    }
  }
}

@Composable
fun Compo2() {
  Text("Compo 2", modifier = Modifier.background(LocalColor.current))
  CompositionLocalProvider(LocalColor provides Color.Magenta) {
    Compo4()
  }
}

@Composable
fun Compo3() {
  Text("Compo 3", modifier = Modifier.background(LocalColor.current))
  CompositionLocalProvider(LocalColor provides Color.Red) {
    Compo5()
  }
}

@Composable
fun Compo4() {
  Text("Compo 4", modifier = Modifier.background(LocalColor.current))
  CompositionLocalProvider(LocalColor provides Color.Cyan) {
    Compo6()
  }
}

@Composable
fun Compo5() {
  Text("Compo 5", modifier = Modifier.background(LocalColor.current))
  CompositionLocalProvider(LocalColor provides Color.Green) {
    Compo7()
  }
  CompositionLocalProvider(LocalColor provides Color.Yellow) {
    Compo8()
  }
}

@Composable
fun Compo6() {
  Text("Compo 6", modifier = Modifier.background(LocalColor.current))
}

@Composable
fun Compo7() {
  Text("Compo 7", modifier = Modifier.background(LocalColor.current))
}

@Composable
fun Compo8() {
  Text("Compo 8", modifier = Modifier.background(LocalColor.current))
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DarkPreview() {
  CompositionLocal()
}

@Preview(showBackground = true)
@Composable
fun LightPreview() {
  CompositionLocal()
}