package com.example.onboarding.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.onboarding.ui.widgets.PaginationDot
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding(modifier: Modifier = Modifier) {
  val pageCount = 4
  val pagerState = rememberPagerState(pageCount = { pageCount })
  val coroutineScope = rememberCoroutineScope()

  Scaffold(
    topBar = { CenterAlignedTopAppBar(title = { PaginationDot(total = pageCount, current = pagerState.currentPage) }) },
    content = { innerPadding ->
      Column(modifier = modifier.padding(innerPadding)) {
        HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
          Text("Page $page")
        }
        Button(onClick = {
          coroutineScope.launch {
            val nextPage = pagerState.currentPage + 1
            if (nextPage < pageCount) {
              pagerState.animateScrollToPage(nextPage)
            }
          }
        }) {
          Text("Next")
        }
      }
    })
}