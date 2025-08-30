package com.logmind.viewbindingsample

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.logmind.viewbindingsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root

        setContentView(binding.root)

        onClickButton()
    }

    fun onClickButton() {
        binding.button.setOnClickListener {
            when (binding.button.text) {
                "Button" -> binding.button.text = "Clicked"
                "Clicked" -> binding.button.text = "Button"
            }
        }
    }
}