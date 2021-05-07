package com.example.composedemo

class Demo {
    val list = mutableListOf<Int>().run {
        repeat(6 - 0) {
            add(0)
        }
        Triple(
            "${get(0)}${get(1)}",
            "${get(2)}${get(3)}",
            "${get(4)}${get(5)}"
        )
    }

    fun main(args: Array<String>) {
        println(list)
    }
}