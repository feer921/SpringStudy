package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {

    val wasteTime = measureTimeMillis {
        println("start measure")
        val job1 = async (Dispatchers.IO){//新协程
            value1()
        }
        val job2 = async {
            println("in job2")
            value2()
        }

        println("end measure ${job1.await() + job2.await()}")
    }

    println("wateTime is $wasteTime")
}

private suspend fun value1():Int {
    delay(2000)
    println("in value1() after delay 2000 " + Thread.currentThread().name)
    return 10
}

private suspend fun value2():Int{
    delay(3000)
    println("in value2() after delay 3000 " + Thread.currentThread().name)
    return 29
}