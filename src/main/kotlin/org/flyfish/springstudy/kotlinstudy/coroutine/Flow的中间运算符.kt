package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * [Flow]的中间运算符
 * [Flow]的中间运算符的思想与Java Stream完全一致.
 * [Flow]与[Sequence]之间在中间运算符上的重要差别在于：对于Flow来说，
 * 这些中间运算符内的代码块是可以调用的地址函数的。
 */
private suspend fun myExecution(input: Int): String {
    delay(1000)
    return "output: $input"

}

fun main() = runBlocking {
    (1..10).asFlow().filter {
        it > 5
    }.map {item ->
                myExecution(item)
    }.collect { println(it) }
    testTransform()
}

private fun testTransform() {
    (1..10).asFlow().transform {input->
        // 在该函数下，可以执行各种逻辑

        emit("my input: $input")
        emit(myExecution(input))
        emit("hello world")
    }
}

