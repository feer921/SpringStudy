package org.flyfish.springstudy.kotlinstudy


/**
 * 解构声明
 * 个人理解：一种通过解析对象构造的方法简便创建出已有对象的一种声明
 */

data class MyServerResult(val resultDesc: String, val statusCode: Int)

fun respMyResult(): MyServerResult {
    return MyServerResult("Success", 200)
}

fun myResult2(): Pair<String, Int> {
    return Pair("Success", 200)//相当于一个方法返回两个值
}
fun main(args: Array<String>) {
    //正常的变量声明对比
    val myResult = respMyResult()
    println(myResult.resultDesc)
    //解构声明，首先是一种语法上的声明
    val (result, status)/* 这里就是解构声明 */ = respMyResult()
    //则可以
    println(result)//直接取出 result 的值


    val (result2, status2 /* 不能和上面重复了*/) = myResult2()






}