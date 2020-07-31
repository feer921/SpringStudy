package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

/**
 * 使用 JVM 参数：-Dkotlinx.coroutines.debug 配置在运行配置的VM 处，可以使输出 线程名称的同时，也输出当前执行的协程名称
 *
 */

private fun log(logMsg: String) = println("[${Thread.currentThread().name}]: $logMsg")

fun main() = runBlocking<Unit>{
    log("run blocking start")//--> [main @coroutine#1]: run blocking start
    val deferred1 = async {
        log("deferred1 start")
        20
    }

    val deferred2 = async {
        log("deferred2 start")
        39
    }

    log("the result is ${deferred1.await() * deferred2.await()}")

}

