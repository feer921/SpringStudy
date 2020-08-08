package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * [Flow]的完成
 * Kotlin提供了两种方式来实现[Flow]的完成
 * 1、命令式: try{}finally{}
 * 2、声明式: 对于声明式方式来说，Flow提供了一个名为[onCompletion],其本质为一个中间操作，
 * 但是其却是在Flow的终止操作执行完后，才执行
 * [onCompletion] 中间操作的一个优势在于它有一个可空的[Throwable]参数，
 * 可用作确定[Flow]的收集操作是正常完成还是异常完成的
 * 类似于 [catch]运算符，[onCompletion]只会看到来自于[Flow]上游的异常，但是它看不到[Flow]下游的异常。
 *
 */

private fun flowMethod():Flow<Int> = flow {
    emit(1)
    emit(2)
    throw RuntimeException()
}
private fun myMethod() = (1..10).asFlow()
fun main() = runBlocking<Unit> {
//    var s: String? = null
//    println(s?.length)//-->null

    //命令式：
//    try {
//        myMethod().collect {
//            println(it)
//        }
//    }finally {
//        println("finally")
//    }

    //声明式：
//    myMethod().onCompletion {
//        println("onCompletion")//会在 collect操作执行完成后执行
//    }.collect {
//        println(it)
//    }

    flowMethod().onCompletion { cause ->
        cause?.let {
            println("Flow completed exceptionally $it")
        }
    }.catch {
        println("catch entered")
    }.collect {
        println("in collect it: $it")
        check(it <= 1) {
            "Collected $it" //这里[Flow]下游如果有异常了，不会传递给 [onCompletion]
        }
    }
}