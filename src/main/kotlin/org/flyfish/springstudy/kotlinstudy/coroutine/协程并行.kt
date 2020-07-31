package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * [async] 与[await] 实现并发
 * 在Js中为关键字，而在Kotlin中为函数
 * 从概念上说，[async]就像 [launch]一样，它会单独开启一个协程，这个协程是一个轻量级的线程，可以与其他协程并发工作，
 *  与[launch]区别在于，[lauch]会返回一个[Job],但是[Job]并不会持有任何的结果值，而[async]会返回一个[Deferred],
 *  这是一个轻量级的非阻塞的 [future],它代表一个[promise],可以在稍后提供一个结果值。
 *  可以通过在一个[Deferred]对象上调用[await]方法来获取最终的结果值。[Deferred]:[Job],也是一个[Job],因此可以在需要的时候进行取消
 *
 */
fun main() = runBlocking {
    val elapsedTime = measureTimeMillis {
        val deferred1 = async {
            intValue1()
        }
        val deferred2 = async {
            intValue2()
        }
        val value1 = deferred1.await()
        val value2 = deferred2.await()

        println("$value1 + $value2 = ${value1 + value2}")
    }
    println("waste total time = $elapsedTime")

}

private suspend fun intValue1():Int{
    delay(2000)
    return 12
}

private suspend fun intValue2():Int{
    delay(3000)
    return 20
}