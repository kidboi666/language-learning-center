package com.example.navigationdemo.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RowColumn() {
    Row {
        TextCell("1", Modifier.weight(0.2f, true))
        TextCell("2", Modifier.weight(0.4f, true))
        TextCell("3", Modifier.weight(0.3f, true))
    }
}

@Composable
private fun TextCell(text: String, modifier: Modifier = Modifier) {
    val cellModifier = Modifier
        .padding(4.dp)
        .size(80.dp, 80.dp)
        .border(width = 4.dp, color = Color.Black)

    Text(
        text = text,
        cellModifier.then(modifier),
        fontSize = 70.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun TextCell2() {
    Row {
        Text(
            text = "Large Text \nMore Text",
            modifier = Modifier.alignBy(FirstBaseline),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Small Text",
            modifier = Modifier.paddingFrom(
                alignmentLine = LastBaseline,
                before = 80.dp,
                after = 0.dp
            ),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
}