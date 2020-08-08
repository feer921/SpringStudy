package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.*

val threadLocal = ThreadLocal<String>()

fun main() = runBlocking<Unit> {
    threadLocal.set("hello")
    println("pre main,currten thread: ${Thread.currentThread()},thread local value : ${threadLocal.get()}")//hello

    val job = launch(Dispatchers.Default + threadLocal.asContextElement("world")) {
        println("lauch start ,currten thread: ${Thread.currentThread()},thread local value : ${threadLocal.get()}")//world
        yield()//让执行本协程的线程 让步而去执行其他可执行的
        //yield()后，执行下面的又是另一个线程
        println("lauch end ,currten thread: ${Thread.currentThread()},thread local value : ${threadLocal.get()}")//world
    }
    job.join()

    println("post main,currten thread: ${Thread.currentThread()},thread local value : ${threadLocal.get()}")//hello
}