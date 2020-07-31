package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.*

/**
 * 使用 JVM 参数：-Dkotlinx.coroutines.debug 配置在运行配置的VM 处，可以使输出 线程名称的同时，也输出当前执行的协程名称
 *
 */

private fun log(logMsg: String) = println("[${Thread.currentThread().name}]: $logMsg")

fun main()  {
     newSingleThreadContext("Context1").use /* 该函数为[Closeable]的扩展函数，
      会自动释放 [Closeable]实例
      */{
//         executorCoroutineDispatcher ->
         context1 ->
         newSingleThreadContext("Context2").use {
             context2 ->
             runBlocking (context1){
                 log("started in context1")
                 withContext(context2){
                    log("working in context2 ")
                 }
                 log("back to context1")
             }
         }

         //这里的 it 为调用[use]函数的实例自身
     }

}

