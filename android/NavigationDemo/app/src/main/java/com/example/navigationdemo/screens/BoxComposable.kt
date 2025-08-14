package com.example.navigationdemo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BoxComposable() {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            contentAlignment = Alignment.TopStart, modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Text("TopStart", Modifier.align(Alignment.TopStart))
            Text("TopCenter", Modifier.align(Alignment.TopCenter))
            Text("TopEnd", Modifier.align(Alignment.TopEnd))

            Text("CenterStart", Modifier.align(Alignment.CenterStart))
            Text("Center", Modifier.align(Alignment.Center))
            Text("CenterEnd", Modifier.align(Alignment.CenterEnd))

            Text("BottomStart", Modifier.align(Alignment.BottomStart))
            Text("BottomCenter", Modifier.align(Alignment.BottomCenter))
            Text("BottomEnd", Modifier.align(Alignment.BottomEnd))
        }
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color.Blue)
        )
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(Color.Blue)
        )
        Box(modifier = Modifier
            .size(200.dp)
            .clip(CutCornerShape(30.dp))
            .background(Color.Blue))
    }
}


@Composable
private fun TextCell(text: String, modifier: Modifier = Modifier, fontSize: Int = 150) {
    val cellModifier = Modifier
        .padding(4.dp)
        .border(width = 5.dp, color = Color.Black)

    Surface {
        Text(
            text = text,
            modifier = cellModifier.then(modifier),
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}