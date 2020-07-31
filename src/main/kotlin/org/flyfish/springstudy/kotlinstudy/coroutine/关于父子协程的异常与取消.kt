package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * 关于父子协程的异常与取消问题
 * 协程的取消总是会沿着协程层次体系向上进行传播
 */
fun main() = runBlocking<Unit> {//父协程
    try {
        failureComputation()//内部有子协程,如果内部协程有异常或者取消，则外层协程也抛出异常或者取消
    }finally {
        println("Computation failed")
    }
}

private suspend fun failureComputation():Int = coroutineScope {
    val deferred1 = async<Int> {
        try {
            delay(9999999999)
            90
        }finally {
            println("value1 was canceled")
        }
    }
    val deferred2 = async<Int> {
        Thread.sleep(3000)
        println("deferred2  throws an exception")
        throw Exception()
    }
    deferred1.await() + deferred2.await()
}