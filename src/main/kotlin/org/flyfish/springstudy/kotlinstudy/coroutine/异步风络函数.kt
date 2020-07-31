package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * 异步风格函数
 */
fun main() {
    val elapseTime = measureTimeMillis {
        val deferred1 = intValue1Async()
        val deferred2 = intValue2Async()
        //但这里可能存在一个问题，如果这里还有 代码执行，如果抛出异常情况下

        //....
        runBlocking {
            "the result is ${deferred1.await() + deferred2.await()/* 因为该函数为挂起函数，所以需要在协程中 */}"
        }
    }

    println("total time = $elapseTime")
}

private suspend fun intValue1():Int{
    delay(2000)
    return 12
}

private suspend fun intValue2():Int{
    delay(3000)
    return 20
}

/**
 * 异步风格函数
 * 这样这里就变成了一个普通方法
 */
fun intValue1Async() = GlobalScope.async {
    intValue1()
}
fun intValue2Async() = GlobalScope.async {
    intValue2()
}