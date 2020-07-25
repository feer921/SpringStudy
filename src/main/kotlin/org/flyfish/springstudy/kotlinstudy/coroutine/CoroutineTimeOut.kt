package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.*

/**
 * 协程的超时
 * 我们在使用协程的时候，如果取消了协程，那么很大一部分原因都在于协程的执行时间超过了某个设定值；
 * 我们可以通过手工引用与协程对应的[Job]的方式来启动一个单独的协程以用于取消这个协程(简单的说就是构建协程的返回对象[Job],可以用来取消这个协程)
 * ，不过Kotlin提供了一个内建的函数来帮助我们又快又好地做到这一点。[withTimeout]
 *
 * 由[withTimeout]函数调用所抛出的 [TimeoutCancellationException]是[CancellationException] 的子类
 * 当该异常抛出时，我们并未在控制台上看到整个异常堆栈信息，这是因为在取消的协程当中，[CancellationException]被认为是一种协程完成的正常原因而已。
 * 不过，在下面的示例中，是在main函数中使用了[withTimeout] 函数调用
 * 既然[CancellationException] 仅仅只是个异常而已，所有的资源也都会以通常的方式来关闭，那么我们就可以将相关代码放在一个try...catch块中；
 * 此外，Kotlin还提供了另外一个更加友好的函数调用:[withTimeoutOrNull] ;从功能角度来看，它非常类似于[withTimeout],不过当超时发生时
 * 它并不会抛出[CancellationException]异常，而是会直接返回 null。
 * 对于[withTimeout]函数调用来说，如果将其放置到try...catch块中，那么调用形式如下：
 * try{
     ...
   }catch(ex:[TimeoutCancellationException]){
     ...
   }
 */
fun main() = runBlocking {
    withTimeout(1900){
        repeat(1000) {
            println("hello $it")
            delay(400)
        }
    }//如果到了超时时间，会抛出 [TimeoutCancellationException]

//    withTimeoutOrNull()
}