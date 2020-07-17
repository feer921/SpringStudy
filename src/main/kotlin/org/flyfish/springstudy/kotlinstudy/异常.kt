package org.flyfish.springstudy.kotlinstudy
import java.lang.Integer.parseInt
/**
 * 异常：Kotlin中的 try是个表达式,有返回值
 * Kotlin中没有 checked exception(非运行时异常)
 */
fun main(args: Array<String>) {
    val s = "a"
    val result: Int? = try {
        parseInt(s)//最后一行即为返回值
    } catch (ex: NumberFormatException) {
        null//catch 块内也有返回值
    }finally {
        println("finally")
    }
    println(result)

//    Nothing? 这个类型，惟一的值即为 null,因为如果不为 null时，为Nothing,而Nothing表示没有任何值
    var s1 = null// 那么此时 s1的类型就是 Nothing?,因为编译器推断不出来是什么类型
    println(s1 is Nothing?)// 为true

    var s2 = listOf(null)//同理，编译器也推断不出来 s2 这个Lsit内元素的具体类型
    println(s2 is List<Nothing?>)// 为true

}

/**
 * [throw] 在Kotlin中也是一个表达式，这样我们可以将 [throw] 作为[Elvis] 表达式的一部分
 * [Elvis] 表达式： [?:] 不同于 三目运算符: String  a = true ? "a":"b"
 * [throw]表达式 的类型是一种特殊的类型： [Nothing],并且表示一段 永远不会触达的代码位置
 *
 */
fun testThrow() {
    val str: String? = "a"
    val str2 = str ?:/*[Elvis] 表达式：表示如果str为null,则参考后面  */ throw IllegalArgumentException("值不能为空")


}

fun throwEx(exceptionMsg: String): Nothing {
    throw IllegalArgumentException(exceptionMsg)//[throw]是个表达式，返回类型为[Nothing]
}