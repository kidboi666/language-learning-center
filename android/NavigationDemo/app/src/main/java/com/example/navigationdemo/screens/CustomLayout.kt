package com.example.navigationdemo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

fun Modifier.exampleLayout(fraction: Float) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    val x = -(placeable.width * fraction).roundToInt()

    layout(placeable.width, placeable.height) {
        placeable.placeRelative(x = x, y = 0)
    }
}

@Composable
fun CustomLayout() {
    val coroutineScope = rememberCoroutineScope()

    suspend fun performSlowTask() {
        println("performSlowTask before")
        delay(5000)
        println("performSlowTask after")
    }

    LaunchedEffect(Unit) {
        coroutineScope.launch() {
            performSlowTask()
        }
    }

    Box(modifier = Modifier.size(120.dp, 80.dp)) {
        Column {
            ColorBox(
                Modifier
                    .exampleLayout(0f)
                    .background(Color.Blue)
            )
            ColorBox(
                Modifier
                    .exampleLayout(0.25f)
                    .background(Color.Green)
            )
            ColorBox(
                Modifier
                    .exampleLayout(0.5f)
                    .background(Color.Yellow)
            )
            ColorBox(
                Modifier
                    .exampleLayout(0.25f)
                    .background(Color.Red)
            )
            ColorBox(
                Modifier
                    .exampleLayout(0.0f)
                    .background(Color.Magenta)
            )
        }
    }
}

@Composable
fun ColorBox(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .padding(1.dp)
            .size(width = 50.dp, height = 10.dp)
            .then(modifier)
    ) {

    }
}