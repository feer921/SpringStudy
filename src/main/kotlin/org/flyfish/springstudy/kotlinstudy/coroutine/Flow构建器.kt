package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

/**
 * Flow builder (Flow构建器)
 * 1、[flow] 是经常被使用的一种流构建器
 * 2、[flowOf] 构建器可用于定义能够发射固定数量值的流
 * 3、对于各种集合与序列来说，他们都提供了 [asFlow]扩展方法来将自身转换为[Flow]
 *
 * [inline] [noinline]
 * [crossinline]
 * non-local returns: 非局部返回，实际上表示的是一个方法内部，我们可以在其中通过一个lambda表达式的返回来直接将外层方法返回。
 *
 * [crossinline] 的作用实际上就是表示被标记的lambda表达式 是不允许 非局部返回的
 */

fun main() = runBlocking<Unit>{
    (1..5).asFlow().collect {
        println(it)
    }

    flowOf(1,2,3,5,6)
            .collect {
                println(it)
            }

}