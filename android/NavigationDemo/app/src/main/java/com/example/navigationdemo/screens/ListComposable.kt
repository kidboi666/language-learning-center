package com.example.navigationdemo.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.navigationdemo.R
import com.example.navigationdemo.ui.ImageLoader
import kotlinx.coroutines.launch


@Composable
fun ListComposable() {
    ActionScroll()
}

@Composable
fun ActionScroll() {
    val context = LocalContext.current
    val carNames = context.resources.getStringArray(R.array.car_array)
    val groupedItems = carNames.groupBy { it.substringBefore(" ") }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val displayButton by remember { derivedStateOf { listState.firstVisibleItemIndex > 5 } }

    fun onItemClick(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    Box {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 40.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            groupedItems.forEach { (brand, models) ->
                stickyHeader {
                    Text(
                        text = brand, color = Color.White, modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Gray)
                            .padding(5.dp)
                    )
                }
                items(models) { model ->
                    MyListItem(item = model, onItemClick = ::onItemClick)
                }
            }
        }

        AnimatedVisibility(visible = displayButton, modifier = Modifier.align(Alignment.BottomCenter)) {
            OutlinedButton(
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                },
                border = BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.DarkGray,
                    containerColor = Color.White
                ),
                modifier = Modifier.padding(5.dp)
            ) {
                Text("Top")
            }
        }
    }
}

@Composable
fun MyListItem(item: String, onItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        onClick = { onItemClick(item) }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ImageLoader(item)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = item, style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun RowList() {
    val carNames = LocalContext.current.resources.getStringArray(R.array.car_array)
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row(modifier = Modifier.horizontalScroll(scrollState)) {
            repeat(50) {
                Text(
                    "List Item ${it + 1}",
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
        IconButton(onClick = {
            coroutineScope.launch {
                scrollState.animateScrollTo(scrollState.maxValue)
            }
        }) { Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null) }
    }
}

@Composable
fun ColumnList() {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row {
            Button(
                onClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollTo(scrollState.value - scrollState.maxValue)
                    }
                }, modifier = Modifier
                    .weight(0.5f)
                    .padding(2.dp)
            ) {
                Text("맨 위로")
            }
            Button(
                onClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollTo(scrollState.maxValue)
                    }
                }, modifier = Modifier
                    .weight(0.5f)
                    .padding(2.dp)
            ) {
                Text("맨 아래로")
            }
        }
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            repeat(500) {
                Text("List Item $it")
            }
        }
    }
}

@Composable
fun LazyVerticalGridList2() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3), contentPadding = PaddingValues(10.dp)
    ) {
        items(300) { index ->
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxSize(), elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Text(
                    text = "$index", modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(), textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun LazyVerticalGridList() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 60.dp), contentPadding = PaddingValues(10.dp)
    ) {
        items(300) { index ->
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxSize()
            ) {
                Text(text = "Item $index", modifier = Modifier.padding(10.dp))
            }
        }
    }
}

@Composable
fun StickyFeature() {
    val phones = listOf(
        "Apple iPhone 12",
        "Apple iPhone 7",
        "Apple iPhone 13",
        "Apple iPhone 13",
        "Apple iPhone 13",
        "Apple iPhone 13",
        "Apple iPhone 13",
        "Apple iPhone 13",
        "Apple iPhone 11",
        "Apple iPhone 10",
        "Google Pixel 4",
        "Google Pixel 4",
        "Google Pixel 4",
        "Google Pixel 4",
        "Google Pixel 4",
        "Google Pixel 4",
        "Google Pixel 4",
        "Google Pixel 4",
        "Google Pixel 4",
        "Google Pixel 4",
        "Google Pixel 4",
        "Google Pixel 4",
        "Google Pixel 4",
        "Google Pixel 6",
        "Samsung Galaxy 6s",
        "Samsung Galaxy 6s",
        "Samsung Galaxy 6s",
        "Samsung Galaxy 6s",
        "Samsung Galaxy 6s",
        "Samsung Galaxy 6s",
        "Samsung Galaxy 6s",
        "Samsung Galaxy 6s",
        "Samsung Galaxy 6s",
        "Samsung Galaxy 6s",
        "Samsung Galaxy 6s",
        "Samsung Galaxy 6s",
        "Samsung Galaxy 6s",
        "Samsung Galaxy 6s",
        "OnePlus 7",
        "OnePlus 7",
        "OnePlus 7",
        "OnePlus 7",
        "OnePlus 7",
        "OnePlus 7",
        "OnePlus 7",
        "OnePlus 7",
        "OnePlus 7",
        "OnePlus 7",
        "OnePlus 7",
        "OnePlus 9 Pro",
    )
    val groupedPhones = phones.groupBy { it.substringBefore(" ") }
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val firstVisible = listState.firstVisibleItemIndex

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(state = listState) {
            groupedPhones.forEach { (brand, models) ->
                stickyHeader {
                    Text(
                        text = brand,
                        color = Color.White,
                        modifier = Modifier
                            .background(Color.Gray)
                            .padding(5.dp)
                            .fillMaxWidth()
                    )
                }
                items(models) { item ->
                    Text(text = item, modifier = Modifier.padding(5.dp))
                }
            }
        }
        if (firstVisible > 8) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                }, modifier = Modifier.padding(5.dp)
            ) {
                Text("맨 위로")
            }
        }
    }
}

@Composable
fun AnimateScroll() {
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val colorNamesList = listOf("Red", "Green", "Blue", "Indigo")

    Column(
        Modifier
            .verticalScroll(scrollState)
            .fillMaxWidth()
    ) {
        Button(onClick = {
            coroutineScope.launch {
                scrollState.animateScrollTo(scrollState.maxValue, animationSpec = TweenSpec(durationMillis = 1000))
            }
        }) { Text("맨 아래로") }
        repeat(100) {
            LazyColumn {
                itemsIndexed(colorNamesList) { index, item ->
                    Text("This is item $index = $item")
                }
            }
        }
        Button(onClick = {
            coroutineScope.launch {
                scrollState.animateScrollTo(
                    scrollState.value - scrollState.maxValue, animationSpec = TweenSpec(durationMillis = 1000)
                )
            }
        }) {
            Text("맨 위로")
        }
    }
}
