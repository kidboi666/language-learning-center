package com.example.demoviewmodel.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

class DemoViewModel : ViewModel() {
  var isFahrenheit by mutableStateOf(true)
  var result by mutableStateOf("")

  fun convertTemp(temp: String) {
    result = try {
      val tempInt = temp.toInt()
      if (isFahrenheit) {
        ((tempInt - 32) * 0.5556).roundToInt().toString()
      } else {
        ((tempInt * 1.8) + 32).roundToInt().toString()
      }
    } catch (e: Exception) {
      result = "Invalid Entry"
    } as String
  }

  fun switchChange() {
    isFahrenheit = !isFahrenheit
  }
}