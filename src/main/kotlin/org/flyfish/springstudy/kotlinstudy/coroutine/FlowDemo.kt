package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 如果返回 List<String>结果类型，那么当使用一次性返回所有值的方案。
 * 要想能够表示可以异步计算的流式的值，我们就可以使用[Flow<String>]它非常类似于 [Sequence<String>]类型，但其值是异步计算
 * [Flow] 与Java8的[Stream]
 * 相关
 * 1、Flow构建器是通过[flow]来实现的；
 * 2、位于[flow]构建器中的代码是可以挂起的，eg.:调用[delay]
 * 3、[myMethod]方法无需使用[suspend]标识符，值是通过[emit]函数发射出来的；
 * 4、[Flow]里面的值是通过[collect]函数收集的
 * 5、[Flow]一定要调用它的终止操作,如：[collect]方法 ，Flow才会去执行,并且每次调用都会执行(即多次调用执行多次)
 */
private fun myMethod(): Flow<Int>  = flow {
    for (i in 1..4) {
        delay(100)
        emit(i)//发射，相当于数据计算出来了
    }
}
fun main() = runBlocking<Unit>{
    launch {
        for (i in 1..4) {
            println("hello $i")
            delay(200)
        }
    }
    println("below lauch..")
    val aFlow = myMethod()//调用这个方法时，Flow对象立即返回的，不会管方法内部是否执行[flow并不会去执行]

    aFlow.collect {
        println(it)
    }
}