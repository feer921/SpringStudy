package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking

/**
 * Job的使用方式以及在Context中的具体应用
 * 协程的[Job]归属于其上下文(Context)的一部分，Kotlin为我们提供了一种简洁的手段来通过协程上下文获取到自身的
 * [Job]对象
 * 即我们可以通过[coroutineContext[Job]] 表达式来访问上下文的[Job]对象
 */
fun main() = runBlocking {
    /**
     * [coroutineContext]为 接口[CoroutineScope]的属性
     */
    val job: Job? = coroutineContext[Job]//表达式来访问上下文的[Job]对象
    println(job)
    println(coroutineContext.isActive)
    //上面等价于
    println(coroutineContext[Job]?.isActive)

}