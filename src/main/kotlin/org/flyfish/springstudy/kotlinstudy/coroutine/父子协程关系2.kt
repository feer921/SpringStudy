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
 *
 * 二：对于父协程来说，父协程总是会等待其所有子协程的完成。对于父协程来说，它不必显式地去追踪由它启动的所有子协程，同时也不必调用
 * 子协程的Job.join 方法来等待子协程的完成
 *
 */
fun main() = runBlocking<Unit> {
    val job = launch {
        repeat(5) {
            launch {
                delay((it + 1) * 100L)
                println("Coroutine $it 执行完毕")
            }
        }
        println("hello")
    }
    job.join()//这里会挂起，并且会等待 job内所有子协程完成
    println("world")
}