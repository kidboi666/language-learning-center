package com.example.onboarding.ui.widgets

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PaginationDot(
  current: Int,
  total: Int,
  modifier: Modifier = Modifier,
  activeColor: Color = MaterialTheme.colorScheme.primary,
  inactiveColor: Color = MaterialTheme.colorScheme.surfaceVariant
) {
  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
  ) {
    repeat(total) { index ->
      val color by animateColorAsState(
        targetValue = if (index == current) activeColor else inactiveColor,
        animationSpec = tween(durationMillis = 400),
        label = "DotColorAnimation"
      )
      Box(
        modifier = Modifier
          .padding(horizontal = 2.dp)
          .size(8.dp)
          .clip(CircleShape)
          .background(color)
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun PaginationDotPreview() {
  MaterialTheme {
    PaginationDot(current = 2, total = 5)
  }
}

@Preview(showBackground = true)
@Composable
fun PaginationDotPreview_Another() {
  MaterialTheme {
    PaginationDot(
      current = 0,
      total = 3,
      activeColor = Color.Red,
      inactiveColor = Color.LightGray
    )
  }
}