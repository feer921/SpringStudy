package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

/**
 * [Flow]的组合 [zip]
 */
fun main()= runBlocking <Unit>{
    val flow1OfNums = (1..7).asFlow()
    val flowOfStrs = flowOf(
            "one",
            "tow",
            "three",
            "four",
            "five"
    )
    flow1OfNums.zip(flowOfStrs){
        flow1Item,flow2Item ->
        "$flow1Item -> $flow2Item"
    }.collect {
        result ->
        println(result)
    }

}