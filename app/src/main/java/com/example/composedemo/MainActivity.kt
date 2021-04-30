package com.example.composedemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = MainActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colors.primary) {
                newsStory()
            }
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
    fun newsStory(names: List<String> = List(50) { "Hello Android #$it" }) {
        Column(
            modifier = Modifier.height(height = 300.dp).fillMaxWidth()
                .background(Color.Red)
                .padding(0.dp, 10.dp, 0.dp, 0.dp)
                .clickable(onClick = { clickColumn() })
        ) {
            NameList(names, Modifier.weight(1f))
            //
            val counterState = remember { mutableStateOf(0) }
            Counter(count = counterState.value, update = { newValue ->
                counterState.value = newValue
            })
//            val image = imageFromResource(resources, R.mipmap.ic_launcher)
//            Image(
//                asset = imageFromResource(resources, R.mipmap.ic_launcher),
//                modifier = Modifier.preferredHeightIn(160.dp, 260.dp)
//                    .fillMaxWidth(),
//                contentScale = ContentScale.Crop
//            )
//            Image(
//                painter = painter
//            )
            // 绘图
//            Canvas(modifier = Modifier.fillMaxSize()) {
//                val canvasWidth = size.width
//                val canvasHeight = size.height
//                drawLine(
//                    color = Color.White,
//                    p1 = Offset(dx = 0f, dy = 0f),
//                    p2 = Offset(dx = canvasWidth, dy = 0f),
//                    stroke = Stroke()
//                )
//                drawLine(
//                    color = Color.Blue,
//                    p1 = Offset(dx = canvasWidth, dy = 0f),
//                    p2 = Offset(dx = 0f, dy = canvasHeight),
//                    stroke = Stroke()
//                )
//            }
        }
    }

    @Composable
    fun NameList(names: List<String>, modifier: Modifier = Modifier) {
        LazyColumn(modifier = modifier) {
            items(names.size) { i ->
                Greeting(names[i])
                Divider(color = Color.Cyan, thickness = 10.dp)
            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        var isSelected by remember { mutableStateOf(false) }
        val txtBgColor by animateColorAsState(if (isSelected) Color.Yellow else Color.Transparent)
        Text(
            name,
            modifier = Modifier.background(Color.Blue).clickable {
                isSelected = !isSelected
            }.padding(5.dp)
                .background(color = txtBgColor),
            fontSize = 20.sp
        )
    }

    fun clickText(name: String) {
        Log.i(TAG, "clickText: zzh" + name)
        Toast.makeText(this, "clickText: zzh" + name, Toast.LENGTH_SHORT).show()
    }

    fun clickColumn() {
        Log.i(TAG, "clickColumn: zzh")
    }

    @Preview(showBackground = true, name = "Text Preview")
    @Composable
    fun DefaultPreview() {
//        ComposeDemoTheme {
//        }
        newsStory()
    }

    @Composable
    fun Counter(count: Int, update: (Int) -> Unit) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Button(
                onClick = { update(count + 1) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (count > 5) Color.Gray else Color.White
                ),
            ) {
                Text("you have click ${count} times")
            }
        }
    }
}