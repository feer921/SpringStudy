package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.CancellationException

/**
 * 协程的取消与超时
 */
fun main() = runBlocking {
    val job = launch {
        repeat(200){
            println("hello $it")
            delay(500)
        }
    }
    delay(1000)
    job.cancel(CancellationException("just a try"))
    job.join()//将 job汇总到本线程上来了，目的是让 cancel()后，让协程真正结束
    //或者使用
//    job.cancelAndJoin()
    println("Welcome")
}