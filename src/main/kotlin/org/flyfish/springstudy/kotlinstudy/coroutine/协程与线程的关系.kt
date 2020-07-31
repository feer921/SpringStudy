package org.flyfish.springstudy.kotlinstudy.coroutine

import kotlinx.coroutines.*
import java.util.concurrent.Executors

/**
 * 协程与线程之间的关系
 * 协程上下文与分发器(Coroutine Context与 Dispatcher)
 * 协程总是会在某个上下文中执行，这个上下文实际上是由[CoroutineContext]类型的一个实例来表示的，该实例是由Kotlin标准库来定义的
 * 协程上下文本质上是各种元素所构成的一个集合(不是List)。其中主要元素包括协程的[Job],以及分发器。
 * 所谓分发器，其主要功能就是确定由哪个线程来执行我们所指定的协程代码
 * 协程上下文包含了一个协程分发器[CoroutineDispatcher],协程分发器确定了到底由哪个线程或者线程池来执行我们所指定的协程。协程分发器可以将
 * 协程的执行限制到一个具体指定的线程，也可以将协程的执行分发到一个线程池中，由线程池中的某个线程来执行我们所指定的协程，还可以不加任何限制
 * 地去执行我们所指定的协程代码(在这种情况下，协程代码到底是由哪个线程或者线程池来执行的是不确定的，它需要根据程序的实际执行情况方能确定，这种方式
 * 的协程分发器在一般的开发中使用较少的，它只是用在一些极为特殊的情况下)。
 *
 * 所有协程的构建器(coroutine builder)如[launch]和[async]都会接收一个可选的[CoroutineContext]参数，该参数可用于显式指定新协程所
 * 运行的分发器以及其他上下文元素。
 *
 * 示例分析：
 * 1、当通过[launch]来启动协程并且不指定协程分发器时，它会继承启动它的那个[CoroutineScope]的上下文与分发器。对于本示例来说，它会继承
 * [runBlocking]的上下文，而[runBlocking]运行在main线程当中。
 * 2、[Dispatchers.Unconfined] 是一种很特殊的协程分发器，它在该示例也是运行在man线程中，但实际上，其运行机制与不指定协程分发器时是完全不同的。
 *                             在日常开发中使用较少
 * 3、[Dispatchers.Default]  是默认的分发器，当协程是通过[GlobalScope]来启动的时候，它会使用该默认的分发器来启动协程，它会使用一个后台的
 * 的共享线程池来运行我们的协程代码。因此，[launch(Dispatchers.Default)] 等价于[GlobalScope.launch]{}
 *
 * 4、Executors.newSingleThreadExecutor().asCoroutineDispatcher() 创建一个单线程的线程池，该线程池中的线程用来执行我们的协程代码，
 * ；在实际开发中，使用专门的线程来执行协程代码代价是非常高的，因此在协程代码执行完毕后， 我们必须要释放相应的资源，这里就需要使用close方法来关闭
 * 相应的协程分发器 ，从而释放资源；也可以将该协程分发器存储到一个顶层变量当中，以便在程序的其他地方复用
 *
 */
fun main() = runBlocking<Unit> {
     launch {
         val curThread = Thread.currentThread()
         println("no param,thread: ${curThread.name}")//-->main: 它会继承[runBlocking]的上下文与分发器
     }

    launch(Dispatchers.Unconfined/* [Dispatchers]为一个object，对象，Dispatchers.Unconfined为接口[CoroutineContext]的子类 */){
        delay(100)//如果加了这个，则本协程可能不运行在 main线程中
        val curThread = Thread.currentThread()
        println("Dispatchers.Unconfined,thread: ${curThread.name}") //-->main，这里碰巧运行在 main线程当中
    }

    launch (Dispatchers.Default) {
        val curThread = Thread.currentThread()
        println("Dispatchers.Default,thread: ${curThread.name}")//--> DefaultDispatcher-worker-1
    }
    GlobalScope.launch {
        val curThread = Thread.currentThread()
        println("GlobalScope.launch,thread: ${curThread.name}")//--> DefaultDispatcher-worker-1
    }

//    launch(Executors.newSingleThreadExecutor().asCoroutineDispatcher()) {
//        val curThread = Thread.currentThread()
//        println("Single thread executor,thread: ${curThread.name}")//--> pool-1-thread-1
//    }

    //上面的代码运行时，Jvm一直处于运行状态，不会退出，是因为 Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    val threadDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    launch(threadDispatcher){
        threadDispatcher.close()//调用close来关闭该协程分发器
    }
}