package com.example.composedemo.countdownAnimate

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*

enum class Screen {
    Input, Countdown
}

class CountdownAnimateAC : AppCompatActivity() {
    companion object {
        val TAG = CountdownAnimateAC::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }

    @Composable
    fun MyApp() {
        var timeInSec = 0
        Surface(color = MaterialTheme.colors.background) {
            var screen by remember { mutableStateOf(Screen.Input) }
            when (screen) {
                Screen.Input -> {
                    Text(TAG + "MyApp: screen")
                }
                Screen.Countdown -> {
                    Text(TAG + "MyApp: Countdown")
                }
            }
        }
    }
}