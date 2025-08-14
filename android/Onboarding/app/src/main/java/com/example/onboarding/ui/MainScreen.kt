package com.example.moodlog.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val moods by viewModel.moods.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (moods.isEmpty()) {
            Text(text = "No moods yet!", modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn {
                items(moods) { mood ->
                    Text(text = "${mood.date}: ${mood.feeling}")
                }
            }
        }
    }
}
