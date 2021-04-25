package com.example.composedemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Canvas
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.foundation.drawBackground
import androidx.ui.geometry.Offset
import androidx.ui.graphics.Color
import androidx.ui.graphics.drawscope.Stroke
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.padding
import androidx.ui.res.imageResource
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = MainActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            newsStory()
        }
        /**
         * Jetpack Compose是围绕composable函数来构建的。
         * 这些函数使你可以通过描述应用程序的形状和数据依赖，
         * 以编程方式定义应用程序的UI，而不是着眼于UI的构建过程。
         * 要创建composable函数，只需要在函数名前面加上一个@composable注解即可, 上面的Text就是一个composable函数。
         */
    }

    /*
    * 一个composable函数只能在另一个composable函数的作用域里被调用，要使一个函数变为composable函数，只需在函数名前加上@composable注解
    * */
    @Composable
    fun newsStory() {
//        var image = imageResource(R.mipmap.ic_launcher)
        Column(
            modifier = Modifier.drawBackground(Color.Red).padding(10.dp, 30.dp, 0.dp, 0.dp)
                .fillMaxSize()
                .clickable(onClick = { clickColumn() })
        ) {
            Text(
                "abcde",
                modifier = Modifier.drawBackground(Color.Yellow),
                fontSize = TextUnit.Sp(20)
            )
            Text(
                "fghij",
                modifier = Modifier.drawBackground(Color.Blue),
                fontSize = TextUnit.Sp(40)
            )
            Text("klmno", modifier = Modifier.drawBackground(Color.Green))
            // 绘图
            Canvas(modifier = Modifier.fillMaxSize()) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                drawLine(
                    color = Color.White,
                    p1 = Offset(dx = 0f, dy = 0f),
                    p2 = Offset(dx = canvasWidth, dy = 0f),
                    stroke = Stroke()
                )
                drawLine(
                    color = Color.Blue,
                    p1 = Offset(dx = canvasWidth, dy = 0f),
                    p2 = Offset(dx = 0f, dy = canvasHeight),
                    stroke = Stroke()
                )
            }
        }
    }

    fun clickColumn() {
        Log.i(TAG, "clickColumn: zzh")
    }
}