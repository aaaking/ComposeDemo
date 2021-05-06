package com.example.composedemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = MainActivity::class.simpleName
    }

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colors.primary) {
//                val viewModel: ConversationViewModel = viewModel()
//                val names by viewModel.messages.observeAsState()
                NewsStory()
            }
        }
    }

    @ExperimentalAnimationApi
    @Composable
    fun NewsStory(names: List<String> = List(50) { "Hello Android #$it" }) {
        Column(
            modifier = Modifier.height(height = 300.dp).fillMaxWidth()
                .background(Color.Red)
                .padding(0.dp, 0.dp, 0.dp, 0.dp)
                .clickable(onClick = { clickColumn() })
        ) {
            var expanded by remember { mutableStateOf(false) }
            Column(Modifier.clickable { expanded = !expanded }) {
                Image(
                    painterResource(R.mipmap.test),
                    "aweg",
                    modifier = Modifier.height(60.dp).width(60.dp)
                        .clip(shape = RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )
                AnimatedVisibility(expanded) {
                    Text(text = "awpojgporp")
                }
            }
            NameList(names, Modifier.weight(1f))
            //
            val counterState = remember { mutableStateOf(0) }
            Counter(count = counterState.value, update = { newValue ->
                counterState.value = newValue
            })
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
            fontSize = 20.sp,
            style = MaterialTheme.typography.h1
        )
    }

    fun clickText(name: String) {
        Log.i(TAG, "clickText: zzh" + name)
        Toast.makeText(this, "clickText: zzh" + name, Toast.LENGTH_SHORT).show()
    }

    fun clickColumn() {
        Log.i(TAG, "clickColumn: zzh")
    }

    @ExperimentalAnimationApi
    @Preview(showBackground = true, name = "Text Preview")
    @Composable
    fun DefaultPreview() {
//        ComposeDemoTheme {
//        }
        NewsStory()
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