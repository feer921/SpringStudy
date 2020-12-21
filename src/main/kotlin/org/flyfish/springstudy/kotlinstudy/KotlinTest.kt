package org.flyfish.springstudy.kotlinstudy

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.io.Serializable

fun main(args: Array<String>) {

//    opt(1, 3) { a, b ->
//        a * b
//    }

    val str = " "
    //如果 是 str = "",则都是 true
    println("str.isBlank() ? ${str.isBlank()}") //true
    println("str.isEmpty() ? ${str.isEmpty()}") //false
    for (index in 0 until 5){
        println(index)
    }


//    for (index in 0..5) {// 0.rangeTo()是一致的
//        println(index)
//    }

//    val a = arrayOf("ab", "cd", "ddd", "bbb")
//            .groupBy {
//                it.length
//            }
//    println(a.javaClass)
//    runBlocking {
//        try {
//            failCal()
//        }finally {
//            println("fail Cal ")
//        }
//    }
}

fun add(x: Int, y: Int) = x + y

fun concat(a: String, b: String) = a + b

fun <T,R> opt(x: T, y: T, action: (a: T, b: T) -> R): R {
    return action(x, y)
}

fun testWhen(str :String):String {
    //一：
//    when (str) {
//        "hello" -> return "HELLO"
//        "world" -> return "WORLD"
//        "hello world" -> return "HELLOWORLD"
//        else -> return "other input"
//    }

    return when (str) {
        "hello" -> "HELLO"
        "world" -> "WORLD"
        "hello world" -> "HELLOWORLD"
        else -> "other input"
    }
}

private suspend fun failCal():Int {
   return coroutineScope {
        val value1 = async {
            try {
                delay(90000000)
                50
            }finally {
                println("value1 was canceld")
            }
        }
       val value2 = async<Int> {
//           Thread.sleep(2000)
           println("value2 will throw a exception")
           throw Exception()
       }
       val reslut = value2.await()
       println("will cal reslut")
       value1.await() + value2.await()
    }
}