package com.example.composedemo.countdownAnimate

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp

@Composable
fun CountdownScreen(timeInSec: Int, onCancel: () -> Unit) {
    var trigger by remember { mutableStateOf(timeInSec) }
    val elapsed by animateIntAsState(
        targetValue = trigger * 1000,
        animationSpec = tween(timeInSec * 1000, easing = LinearEasing)
    )
    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp)
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(70.dp)
                .shadow(30.dp, shape = CircleShape)
                .clickable { onCancel() },
            imageVector = Icons.Default.Cancel,
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
        )
    }
}