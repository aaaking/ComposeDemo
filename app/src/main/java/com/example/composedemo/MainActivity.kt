package com.example.composedemo

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: HelloViewModel
    lateinit var recyclerView: Any
    lateinit var listState: LazyListState
    lateinit var coroutineScope: CoroutineScope

    companion object {
        val TAG = MainActivity::class.simpleName
    }

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            listState = rememberLazyListState()
            coroutineScope = rememberCoroutineScope()
            viewModel = viewModel()
            val names by viewModel.names.observeAsState()
            Surface(color = MaterialTheme.colors.primary) {
                NewsStory(names!!)
            }
        }
    }

    @ExperimentalAnimationApi
    @Composable
    fun NewsStory(names: List<String>) {
        Column(
            modifier = Modifier.height(height = 500.dp).fillMaxWidth()
                .background(Color.Red)
                .padding(0.dp, 0.dp, 0.dp, 0.dp)
                .clickable(onClick = { clickColumn() })
        ) {
            var expanded by rememberSaveable { mutableStateOf(false) }
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
                viewModel.addItem("add ".plus(newValue))
                coroutineScope.launch {
//                    listState.scrollToItem(
//                        viewModel.names.value!!.size,
//                        50 * Resources.getSystem().displayMetrics.density.toInt()
//                    )
                    listState.animateScrollBy(200000f) // can scroll to bottom
                }
            })
            // 绘图
            Canvas(modifier = Modifier.fillMaxWidth().height(100.dp)) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                drawLine(
                    start = Offset(x = 0f, y = 0f),
                    end = Offset(x = canvasWidth, y = 0f),
                    color = Color.White
                )
                drawLine(
                    start = Offset(x = canvasWidth, y = 0f),
                    end = Offset(x = 0f, y = canvasHeight),
                    color = Color.Blue
                )
                drawCircle(Color.Yellow, style = Stroke(1f))
            }
        }
    }

    @Composable
    fun NameList(names: List<String>, modifier: Modifier = Modifier) {
        recyclerView = LazyColumn(
            state = listState,
            modifier = modifier.background(Color.Yellow),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(names.size, key = { i -> i }) { i ->
                Greeting(names[i])
                Divider(color = Color.Cyan, thickness = 2.dp)
            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        var isSelected by remember { mutableStateOf(false) }
        val txtBgColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)
        Text(
            name,
            modifier = Modifier.background(Color.Blue).clickable {
                isSelected = !isSelected
            }.height(38.dp).padding(2.dp)
                .background(color = txtBgColor),
            fontSize = 20.sp,
            style = MaterialTheme.typography.h1
        )
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
        NewsStory(List(50) { "Hello $it" })
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
                val str = "you have click ${count} times"
                Text(str)
            }
        }
    }

    class HelloViewModel : ViewModel() {
        private val _names =
            MutableLiveData<MutableList<String>>(MutableList(20) { "Hello Android #$it" })
        val names: LiveData<MutableList<String>>
            get() = _names

        fun addItem(item: String) {
            _names.value?.add(item)
        }
    }
}