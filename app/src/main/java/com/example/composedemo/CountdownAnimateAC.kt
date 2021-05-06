package com.example.composedemo

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text

class CountdownAnimateAC : AppCompatActivity() {
    companion object {
        val TAG = CountdownAnimateAC::class.java.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text("11111")
        }
    }
}