package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.runBlocking

/**
 * [Flow]的终止操作 (Terminal Operation)
 * [Flow]的终止操作都是挂起函数，终止操作才会真正开始执行
 * 流的收集。
 * 如下操作:
 * 1、[toList]与[toSet]
 * 2、只获取第一个元素
 * 3、[reduce]
 *
 * [Flow]是顺序执行的
 *
 * 对于[Flow]的收集操作来说，它是运行在调用了终止操作的那个协程上，
 * 默认情况下它是不会启动新的协程的。每个 emit的元素都会由所有中间操作
 * 进行处理(并且是一个一个来重复)，最后再由终止操作进行处理。本质上，就是由上游到了下游。
 *
 *
 */
fun main()= runBlocking {
    val result = (1..4).asFlow()
            .map { it *it }
            .reduce { a, b -> a + b }
    println(result)



    val c = 1
    try {
        val s: String? = c as? String
        println(s)
    } catch (ex: Exception) {
        println(ex)
    }


}

