package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * 挂起函数的组合
 */
fun main() = runBlocking {
//    delay(100)//如果没有在 [runBlocking]代码块下，是报错的，因为挂起函数只能在 协程或者 其他挂起中调用
    val elapsedTime = measureTimeMillis /* 该内联函数为计算 block()中的执行时间 */{
        val value1 = intValue1()
        val value2 = intValue2()
        //上面两个挂起函数会串行，所以效率不高
        println("$value1 + $value2 = ${value1 + value2}")
    }

    println("waste total time = $elapsedTime")
}
private suspend fun intValue1():Int{
    delay(2000)
    return 15
}

private suspend fun intValue2():Int{
    delay(3000)
    return 20
}

