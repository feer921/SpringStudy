package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking

private fun myNumber():Flow<Int> = flow {
    try {
        emit(1)
        emit(2)
        println("hello world")
        emit(3)
    } catch (ex: Exception) {
        println(ex)
    }finally {
        println("finally")
    }
}

fun main() = runBlocking {
    myNumber().take(2)/* 表示只取2个发射过来的值*/.collect {
        println(it)
    }
}