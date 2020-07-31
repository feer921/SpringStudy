package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 关于父子协程的关系
 * 当一个协程是通过另一个协程的[CoroutineScope]来启动的，那么这个协程就会通过[CoroutineScope.coroutineContext]来继承其上下文信息，
 * 同时，新协程的[Job]就会成为父协程[Job]的一个子[Job]; 当父协程被取消执行时，该父协程的所有子协程都会通过的方式一并取消执行；
 * 唯一特例情况：当我们使用[GlobalScope]来启动协程时，对于启动的新协程来说，其[Job]是没有父[Job]的，因此，它就不会绑定到其所启动的那个
 * 范围上，故其可以独立执行(这是一种特殊情况)
 */
fun main() = runBlocking<Unit> {
    val job = launch {
        GlobalScope.launch {
            println("job1: hello")
            delay(1000)
            println("job1 : world")

        }
        launch/* [CoroutineScope]的扩展方法*/ {
            delay(100)
            println("job2: hello")
            delay(1000)
            println("job2: world")
        }
    }
    delay(500)
    job.cancel()//该父协程的取消，并不影响 [GlobalScope.lauch]
    delay(1000)
    println("welcome")
}