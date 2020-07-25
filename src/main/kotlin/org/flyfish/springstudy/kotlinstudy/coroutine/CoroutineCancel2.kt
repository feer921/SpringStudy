package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.*
import java.util.concurrent.CancellationException

/**
 * 协程的取消与超时
 * 1、[kotlinx.coroutines] 包下的挂起函数都是可以取消的
 * 2、他们会检查协程的取消状态，当取消时就会抛出[CancellationException]异常
 * 3、不过，如果协程正在处于某个计算过程当中，并且没有检查取消状态，那它就是无法被取消的
 */
fun main1() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default){
        var nextPrintTime = startTime
        var i = 0
        while (i < 20) {
            //计算代码，当不满足 if条件时，CPU空转
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I am sleeping ${i++}")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300)
    println("hello world")
    job.cancelAndJoin()//验证上面的第3点，无法取消
    println("Welcome")
}

/**
 * 有两种方式可以让计算代码变为可取消的：
 * 1、周期性地调用一个挂起函数，该挂起函数会检查取消状态，比如说使用[yield]函数
 * 2、显式地检查取消状态
 * 如下示例采用第二种方式
 * 其中，[isActive] 是协程的一个扩展属性，它是通过[CoroutineScope]对象添加的
 */
fun main2()= runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default){
        var nextPrintTime = startTime
        var i = 0
        while (isActive) {
            //计算代码，当不满足 if条件时，CPU空转
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I am sleeping ${i++}")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300)
    println("hello world")
    job.cancelAndJoin()//验证上面的第3点，无法取消
    println("Welcome")
}

/**
 * 使用[finally]来关闭资源
 * [join]与[cancelAndJoin] 都会等待所有清理动作完成才会继续往下执行
 */
//fun main() = runBlocking {
//    val myJob = launch {
//        try {
//            repeat(100) {
//                println("job: I am sleeping $it")
//                delay(500)
//
//            }
//        }finally {
//            //清理动作
//            println(" run at finally")
//        }
//    }
//    delay(1300)
//    println("after delay 1300")
//    myJob.cancelAndJoin()
//    println("after cancelAndJoin")
//}

/**
 * 对于下面的示例来说，当我们在协程的[finally]块中使用挂起函数时，会导致出现[CancellationException]异常
 * 原因在于运行着该代码块的协程已经被取消了。通常情况下，这并不会产生什么问题，因为大多数关闭操作(比如说取消一个[Job]、关闭网络连接等)
 * 通常都是非阻塞的，并不需要使用挂起函数；然后在极少数情况下，当我们在一个取消的协程中进行挂起操作时，我们可以将相应的
 * 代码放置到[withContext(NonCancellable){}] 当中，在这种结构中，我们实际上使用了 [withContext]函数与[NonCancellable] 上下文
 */
fun main() = runBlocking {
    val myJob = launch {
        try {
            repeat(100) {
                println("job: I am sleeping $it")
                delay(500)

            }
        }finally {
            //清理动作
            println(" run at finally")
//            delay(1000)
//            println("in finally code block after delay")//这里是不会运行的
            //如果使用
            withContext(NonCancellable/* 一个对象 */) {
                println(" run at finally")
                delay(1000)
                println("in finally code block after delay")//这里就会运行的
            }
        }
    }
    delay(1300)
    println("after delay 1300")
    myJob.cancelAndJoin()
    println("after cancelAndJoin")
}
