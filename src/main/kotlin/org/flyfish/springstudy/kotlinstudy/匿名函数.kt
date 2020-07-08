package org.flyfish.springstudy.kotlinstudy


/**
 * 匿名函数
 * 不常用
 * [fun] 声明，但没有名字的函数
 */
fun main(args: Array<String>) {
    fun(x: Int, y: Int) = x + y

    fun(x: Int, y: Int): Int {
        return x + y
    }

    val string2 = arrayOf("hello", "world", "bye")

    string2.filter(fun(item) = item.length > 3)



    //闭包
    var sum = ""
    string2.filter { it.length > 3 }
            .forEach {
                sum += it
                // lambda 内可以访问外部的 信息，并且可以修改
            }

}