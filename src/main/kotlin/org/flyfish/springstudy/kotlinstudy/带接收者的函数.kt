package org.flyfish.springstudy.kotlinstudy


/**
 * 带接收者的函数字面值
 * Kotlin提供这样一种功能：可以通过指定的 [接收者对象] 来调用一个函数字面值，
 * 在函数内部，你可以调用[接收者对象] 的方法而无需使用任何额外的修饰符
 * 这一点非常类似于 扩展函数
 */
fun main(args: Array<String>) {

    val subtract: Int/* 此处Int即为 [接收者对象] */./* .后面就是函数字面值 */(other: Int) -> Int = { other ->
        this/* 指代调用者，就是[接收者对象]的实例 */ + other
    }
    //个人可以理解为： subtract 为一个函数类型的变量，该函数的声明 为 (other:Int)->Int 即参数为Int类型的，并且函数的返回值为Int类型
    //                                      而该函数的实现为 ={other -> this+other}

    //或者
    val subtract2: Int.(other: Int) -> Int = { other -> this - other }

    println(1.subtract(3))//

    /**
     * 匿名函数 语法可以让我们指定函数字面值的[接收者对象] 。这样，我们就可以先去声明一个带有[接收者对象]的函数类型变量，然后再去使用它
     */
    //注意下面 sum这个变量后而是直接跟 "=",表示 后面整个匿名函数赋值给了 sum
    val sum = fun Int.(other: Int): Int = this + other
    //或者
    val sum2 = fun Int.(other: Int): Int {
        return this + other
    }

    println(1.sum(3))

    println("===========")

    /**
     * 带有 [接收者类型] 的函数的非字面值可以作为参数进入传递，前提是所需要接收函数的地方应该有一个[接收者类型]的参数，
     * 反之亦然。
     * 比如说：类型 String.(Int)->Boolean 与 (String,Int)->Boolean 等价
     *
     */

    val strEqualInt: String.(Int) -> Boolean = { toEqualVale -> this.toIntOrNull() == toEqualVale }

    println("123".strEqualInt(123))


    fun myTest2(op: (String, Int) -> Boolean, str: String, value: Int, b: Boolean) {
        println(op(str, value) == b)
    }
    myTest2(strEqualInt, "13", 12, true)

}