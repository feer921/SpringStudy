package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * [Flow] context(Flow 上下文)
 * Flow的收集动作总是发生在调用协程的上下文当中，这个特性叫做 [Context][Preservation] (上下文保留)
 *
 */
private fun log(msg: String) = println("[${Thread.currentThread().name}],$msg}")


private fun myMethod():Flow<Int> = flow {
    log("start")
    for (i in 1..5) {
        emit(i)
    }
}
fun main() = runBlocking<Unit> {
//    myMethod().collect{
//        //此处的收集动作发生在 runBlocking 这个协程的上下文当中
//        log("collect $it")
//    }
    //但是上面这段程序有问题，如果 myMethod()方法中Flow执行非常耗时，那么就会阻塞 main线程，而应该
    //让耗时的Flow任务运行在其他协程(并且其他线程)

    log("at runBlocking coroutine end...")//上面执行完成了，才执行这里


    myMethod2().collect {
        log("in collect... $it"
        )
    }

}

fun myMethod2() :Flow<Int> = flow {
    log("myMethod2 start")//这里还是调用Flow终止符时的协程当中
//    withContext(Dispatchers.Default){
        log("让Flow中的执行代码在 不同的上下文执行")//[DefaultDispatcher-worker-1 @coroutine#1]
        //但是会抛出异常，因为当 Flow的终止操作与执行(发射)操作不在同一个上下文中，

        //要解决这个异常，需要 使用 [flowOn]才行
        for (i in 1..4) {
            Thread.sleep(100)
            emit(i)
        }
//    }
}.flowOn(Dispatchers.Default)//要解决这个异常，需要 使用 [flowOn]才行
    //这样就能让 Flow的终止操作与