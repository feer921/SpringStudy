package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.coroutines.EmptyCoroutineContext

/**
 * 协程是轻量级的
 * 下面为 协程与线程的对比
 */
//fun main() = runBlocking {
//    repeat(10000){
//        launch {
//            delay(1000)
//            println("A$it") //后续输出这里
//        }
//    }
//    println("Hello world")//先输出 这里
//}

//fun main(args: Array<String>)= runBlocking {
//    //如果是用 线程的方式来实现上面 协程方式的一样的效果，这里就会导致 oom异常，因为 系统开启线程的数量是有限制的
//    repeat(10000){
//        thread {
//            Thread.sleep(1000)
//            println("a")
//        }
//    }
//}

fun main() {
    CoroutineScope(EmptyCoroutineContext).launch {
        delay(3000)
        println("after delay ")
    }

    println("end main")
    Thread.sleep(4000)
}