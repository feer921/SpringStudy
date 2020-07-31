package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * 关于[async]的延迟执行
 * 我们可以通过将[async]函数的[start]参数设置为[CoroutineStar.LAZY]来实现协程的延迟执行
 * 在这种情况下，协程会在两种场景下去执行：调用[Deferred]的[await]方法，或者调用[Job]的[Job.start]方法
 *
 */
fun main() = runBlocking {
    val elapsedTime = measureTimeMillis {
        val defered1 = async(start = CoroutineStart.LAZY){
            intValue1()
        }// 延迟后执行
        val deferred2 = async(start = CoroutineStart.LAZY){
            intValue2()
        }

        println("hello world")
        Thread.sleep(6000)
        defered1.start()//这个才会触发并发执行
        deferred2.start()//这个才会触发并发执行
        val value1 = defered1.await()//并发执行
        val value2 = deferred2.await()//这里也开始执行，这里需要等待 3秒钟，而在先行3秒钟的期间 defered1已经执行完成了
        println("$value1 + $value2 = ${value1 + value2}")
    }

    println("total time = $elapsedTime")
}

private suspend fun intValue1():Int{
    delay(2000)
    return 12
}

private suspend fun intValue2():Int{
    delay(3000)
    return 20
}