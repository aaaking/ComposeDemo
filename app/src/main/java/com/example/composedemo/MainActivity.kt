package com.example.composedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.ui.core.setContent
import androidx.ui.foundation.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContent {
            Text("Hello World")
        }
        /**
         * Jetpack Compose是围绕composable函数来构建的。
         * 这些函数使你可以通过描述应用程序的形状和数据依赖，
         * 以编程方式定义应用程序的UI，而不是着眼于UI的构建过程。
         * 要创建composable函数，只需要在函数名前面加上一个@composable注解即可, 上面的Text就是一个composable函数。
         */
    }
}