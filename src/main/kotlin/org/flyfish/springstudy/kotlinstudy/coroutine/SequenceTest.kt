package org.flyfish.springstudy.kotlinstudy.coroutine

/**
 * [Sequence] 序列
 * 如果在获取每一个元素时都需要执行一定的计算，这种计算是一种阻塞行为，将计算后的多个结果返回给调用端
 * 关于序列的特点：
 * 1.序列中的数据并非像普通集合那样一次性返回给调用端，而是计算完一个数据就返回一个数据
 * 2.序列中计算过程会使用调用时的线程来进行，因此它会阻塞该线程的执行
 */
private fun myMethod():Sequence<Int> = sequence {
    for (i in 100..105) {
        Thread.sleep(1000)
        yield(i)
    }
}
fun main() {
    myMethod().forEach { println(it) }
}