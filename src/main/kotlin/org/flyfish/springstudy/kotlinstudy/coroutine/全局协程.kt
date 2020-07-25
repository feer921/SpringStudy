package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 全局协程类似于守护线程（daemon thread)
 * 使用[GlobalScope] 启动的活动协程并不会保持进程的生命，他们就像守护线程一样
 */
fun main() {
    GlobalScope.launch {
        repeat(100){
            println("I am sleep $it")
            delay(400)
        }
    }
    Thread.sleep(2000)//输出结果为 repeat只会执行 5次，后，本sleep时间到，则线程退出，而 GlobalScope创建的协程并不会再执行
}