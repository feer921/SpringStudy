package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * [flowOn]
 * 借助于 [flowOn],可以让Flow在发射元素时所处的上下文与收集(终止操作)时所处的上下文是不同的。
 * 值得注意的是：[flowOn]运算符本身默认的顺序性
 * 现在，收集操作实际上是发生在一个协程当中，而发射操作在另一个协程当中(这一点至关重要)
 * [flowOn]运算符本质上会改变上下文中的 CoroutineDispatcher，并且为上游的[flow]创建另外一个协程
 *
 */
private fun log(msg: String) = println("[${Thread.currentThread().name}],$msg}")


private fun myMethod(): Flow<Int> = flow {
    log("start")
    for (i in 1..5) {
        Thread.sleep(100)
        log("emit $i")
        emit(i)
    }
}.flowOn(Dispatchers.Default)


fun main() = runBlocking {
//    myMethod().collect {
//        log("in collect $it")
//
//    }

    /**
     * [buffer] 的主要作用在于对发射的缓冲，减少等待时间(发射先到缓冲中(不等待终止操作的处理))
     * [buffer]与[flowOn]之间存在一定的关系：
     * 实际上，[flowOn]运算符本质上在遇到需要改变 CoroutineDispatcher时也会使用同样的缓冲机制，
     * 只不过该示例[myMethod22]并没有改变执行上下文而已。
     */
    val wasteTime = measureTimeMillis {
        myMethod22().buffer().collect {
            delay(200)
            println(it)
        }
    }
    println("at runBlocking end ")
}

//
private fun myMethod22():Flow<Int> = flow {
    for (i in 1..4) {
        delay(199) //模拟了一个较为耗时的操作，该操作之后才开始流元素的发射
        emit(i)
    }
}