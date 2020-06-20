package org.flyfish.springstudy.app

fun main(args: Array<String>) {



    opt(1,3){
        a, b ->
        a * b
    }



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