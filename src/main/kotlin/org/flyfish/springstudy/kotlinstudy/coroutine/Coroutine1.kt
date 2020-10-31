package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * 协程
 * 定义：
 * 1、协程通过将复杂性放入库(Kotlin以独立jar包支持)中来简化异步编程。程序的逻辑可以在协程中顺序地表达，而底层库会为我们解决其异步性；
 * 2、该库可以将用户代码的相关部分包装为回调、订阅相关事件、在不同线程（甚至不同机器）上调度执行，而代码则保持如同顺序执行一样简单。
 * 3、协程就像非常轻量级的线程。线程是由系统调度的，线程切换或线程阻塞的开销都比较大。
 * 4、而协程依赖于线程，但是协程挂起时不需要阻塞线程，几乎是无代价的，协程是由开发者控制的。所以协程也像用户态的线程，非常轻量级，一个
 * 线程中可以创建任意个协程。
 * 5、总而言之：协程可以简化异步编程，可以顺序地表达程序，协程也提供了一种避免阻塞线程并用更廉价、更可控的操作替代线程阻塞的方法——协程挂起。
 *
 * 一些概念：
 */
fun main( ) {
    /**
     * launch 为协程的创建器，所创建的协程并不阻塞当前线程
     * 在后台创建一个新的协程，也可以指定协程调度器
     * 每一个 协程构造器会隐式的把一个 [CoroutineScope]实例添加进 代码块或者作用域scope 范围内
     *
     */
    GlobalScope.launch {
        delay(1000)
        println("Kotlin Corountine")
    }
    println("Hello")
    Thread.sleep(2000)
//    Thread.sleep(500)//如果改成 休眠 500，则输出 顺序 "Hello" -->"World"，因为还没等协程执行，本线程就已经结束了
    println("World")
    //上面 输出 顺序 "Hello" --> "Kotlin Corountine" --> "World"

}

fun test() {
    GlobalScope.launch {

    }
}
//fun main( /*args:Array<String>*//* since 1.3 is not necessary*/
//
//) {
//
//}