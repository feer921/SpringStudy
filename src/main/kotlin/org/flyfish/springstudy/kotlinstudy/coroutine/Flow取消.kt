package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

/**
 * [Flow]的取消
 * Flow的取消实际上与协程的取消之间是一种协同的关系；对于Flow来说，它自身并没有引入任何新的取消的概念
 * 它对于取消是完全透明的。
 * Flow的收集操作是可以取消的：前提是[Flow]在一个可取消的挂起函数(如 [delay])中被挂起了，除此之外，我们无法通过其他方法来取消[Flow]的执行
 */

private fun myMethod(): Flow<Int> = flow {

    for (i in 1..4) {
        delay(100)
        println("emit: $i")
        emit(i)
    }

}
fun main() = runBlocking<Unit> {
    withTimeoutOrNull(280){
        //在 withTimeoutOrNull这个挂起函数内，执行 Flow，因为280秒后超时，所以会让 Flow也取消掉
        myMethod().collect { println(it) }

    }

}