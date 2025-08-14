package com.example.navigationdemo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.navigationdemo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifierUse() {

  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    CustomImage(
      R.drawable.vacation_image,
      Modifier
        .padding(16.dp)
        .width(270.dp)
        .clip(shape = RoundedCornerShape(30.dp))
    )
  }
}

@Composable
fun CustomImage(image: Int, modifier: Modifier = Modifier) {
  Image(
    painter = painterResource(image),
    contentDescription = null,
    modifier
  )
}