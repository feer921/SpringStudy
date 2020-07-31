package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.*

/**
 * [CoroutineScope]
 *
 */

class Activity : CoroutineScope by CoroutineScope(Dispatchers.Default)/* 类委托，该方法为工厂方法 */ {

    fun destroy() {
        cancel()//当调用该 函数时，在 [CoroutineScope] 内创建的其他子协程都会取消掉
    }

    fun doSomething() {
        repeat(8) {
            launch {
                delay((it + 1) * 300L)
                println("Coroutine $it is finish")
            }
        }
    }

}
fun main() = runBlocking {
    val activity = Activity()
    activity.doSomething()
    println("启动协程")
    delay(1300L)

    println("销毁 Activity")

    activity.destroy()

    delay(9000L)

}