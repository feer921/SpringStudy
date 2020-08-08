package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking


/**
 * [Flow] Exception
 */
private fun myMethod():Flow<Int> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i)
    }
}.map {
    check(it <= 1){
        "Crash at it= $it"
    }
    it*2
}

fun main() = runBlocking {
    try {// try catch 能捕获到 [flow]所有阶段的异常
        myMethod().collect {
            value ->
            println("in collect value: $value")
            check(value <= 1){
                "Collected $value"
            }
        }
    } catch (ex: Throwable) {
        println("Caught &ex")
    }
}
