package com.example.navigationdemo.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun ListComposable() {
    val colorNamesList = listOf("Red", "Green", "Blue", "Indigo")
    val scrollState = rememberScrollState()

    Column(Modifier.verticalScroll(scrollState)) {
        repeat(100) {
            MyListItem()
        }
    }
//    LazyColumn {
//        itemsIndexed(colorNamesList) { index, item ->
//            Text("$index = $item")
//        }
//    }
}

@Composable
fun MyListItem(index: Int = 0) {
    Text("This is item $index")
}