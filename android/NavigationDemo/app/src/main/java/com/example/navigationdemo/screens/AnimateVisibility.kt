package com.example.navigationdemo.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimateVisibility() {
    VisibleBox2()
}

@Composable
private fun VisibleBox2() {
    var boxVisible by remember { mutableStateOf(true) }

    fun onClick(newState: Boolean) {
        boxVisible = newState
    }

    Column(
        modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Crossfade(targetState = boxVisible, animationSpec = tween(500)) { visible ->
                when (visible) {
                    true -> CustomButton("Hide", targetState = false, onClick = ::onClick, bgColor = Color.Red)
                    false -> CustomButton("Show", targetState = true, onClick = ::onClick, bgColor = Color.Green)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        AnimatedVisibility(
            visible = boxVisible,
            enter = fadeIn(animationSpec = tween(5000)),
            exit = fadeOut(animationSpec = tween(5000))
        ) {
            Box(
                modifier = Modifier
                    .size(height = 200.dp, width = 200.dp)
                    .background(Color.Blue)
            )
        }
    }

}

@Composable
private fun VisibleBox() {
    val state = remember { MutableTransitionState(false) }

    fun onClick(newState: Boolean) {
        state.targetState = newState
    }

    state.apply { targetState = true }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            CustomButton(text = "Show Box", targetState = true, onClick = ::onClick)
            CustomButton(text = "Hide Box", targetState = false, onClick = ::onClick)
        }
        Spacer(modifier = Modifier.height(20.dp))
        AnimatedVisibility(
            visibleState = state, enter = fadeIn(
                animationSpec = tween(5000, easing = CubicBezierEasing(0.8f, 0f, 0.2f, 1f)),
            ) + expandHorizontally(), exit = slideOutVertically()
        ) {
            Box(
                modifier = Modifier
                    .size(height = 200.dp, width = 200.dp)
                    .background(Color.Blue)
            )
        }
    }
}

@Composable
private fun CustomButton(text: String, targetState: Boolean, onClick: (Boolean) -> Unit, bgColor: Color = Color.Blue) {
    Button(
        onClick = { onClick(targetState) },
        colors = ButtonDefaults.buttonColors(containerColor = bgColor, contentColor = Color.White),
    ) {
        Text(text = text)
    }

}