package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 每一个协程构建器（包括[runBlocking] )都会向其代码块作用域中添加一个[CoroutineScope]实例。
 * 我们可以在该作用域中启动协程，而无需显式将其 join到一起，这是因为外部协程(在下面的示例中就是[runBlocking])
 * 会等待其作用域中所有启动的协程全部完成后才会本协程完成、退出。
 *
 *
 */
//fun main() = runBlocking {
//    launch {
//        delay(1000)
//        println("Kotlin Coroutines")
//    }
//
//    println("hello")//这里输出后，[runBlocking]协程并不会马上退出，而是会等待 launch创建的新协程的完成
//
//}

/**
 *  <P>
 *     除去不同的协程构建器所提供的协程作用域(coroutine scope)外，我们还可以通过 coroutineScope builder来声明自己的的协程作用域，
 *     该构建器会创建一个协程作用域，并且会等待所有启动的子协程全部完成后自身才会完成。
 *     [runBlocking] 与 coroutineScope之间的主要 差别在于：后者在等待所有子协程完成其任务时并不会阻塞当前线程。
 * </P>
 *
 * 2020-07-25 答疑：[runBlocking] 并非挂起函数，也就是说调用它的线程会一直位于该函数之中，直到协程执行完毕为止。
 * 而[coroutineScope]为挂起函数，也就是说，如果其内的协程挂起，那么[coroutineScope]函数也会挂起。这样，创建[coroutineScope]的外层
 * 函数就可以继续在同一个线程中执行了，该线程会[逃离][coroutineScope]之外(下面的例子为跳转到[runBlocking]调用处)，并且可以做其他的一些事情
 */
fun main() = runBlocking {
    launch {
        delay(1000)
        println("my job2")//输出 2

    }

    println("person")//输出 1

    coroutineScope /* 因为内部协程挂起，则本函数也挂起，当前线程返回到runBlocking调用处，监听其他事件执行,所以这里并不是阻塞 */{
        launch {
            delay(20000)
            println("my job2")//输出 4
        }
        delay(5000)
        println("in coroutineScope1")//输出 3
    }
    //线程在未完成[coroutineScope]的任务之前，不会到这里来
    //？？？为什么这里会等待 coroutineScope的完成？ 2020-07-25 答疑：因为[coroutineScope]是挂起函数
    println("welcome")//输出 5
}