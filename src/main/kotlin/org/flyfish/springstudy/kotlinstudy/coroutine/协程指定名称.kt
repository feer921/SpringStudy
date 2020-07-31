package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.*

/**
 * 使用 JVM 参数：-Dkotlinx.coroutines.debug 配置在运行配置的VM 处，可以使输出 线程名称的同时，也输出当前执行的协程名称
 * [CoroutineName] 上下文元素可以让我们对协程进行命名，以便能够输出可读性较好的日志信息
 */
fun log2(logMsg: String) = println("[${Thread.currentThread().name}] $logMsg")

fun main() = runBlocking(CoroutineName("main")) {
    log2("hello")
    val deferred1 = async(CoroutineName("Coroutine1")) {
        log2(" coroutine1")
        delay(1000)
        30
    }

    val deferred2 = async(CoroutineName("Coroutine2")){
        log2("coroutine2 ")
        delay(3000)
        5
    }

    /**
     * 如果 即要指定 协程的名称又要指定协程分发器，则需要使用到 运算符重载
     */
    launch(Dispatchers.Default +/* 这里就是运算符重载 */ CoroutineName("launch coroutine1")){

    }
    println("Result is ${deferred1.await() * deferred2.await()}")


}