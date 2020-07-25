package org.flyfish.springstudy.kotlinstudy

/**
 * 数组(Array)
 * Kotlin中的数组是不变的（相对对协变与逆变来说的),这一点与Java存在明显的不同。
 * 这意味着，我们无法将一个Array<String>赋值给 Array<Any>,这样就可以防止可能出现的运行期异常
 * 而 Java是可以的 Object[] = new String[2]{};
 * Kotlin提供了 Java原生类型 数组的 直接类：IntArray,DoubleArray,CharArray... 来避免自动装箱与自动拆箱带来的成本
 *
 *
 */
fun main(args: Array<String>) {
    val myArray = MyArray()

    val intArray = intArrayOf(1, 3, 3)

    myArray.myArrayMethod(intArray)

    println("-----------")

    //当编译为JVM字节码时，编译器会优化对于数组的访问，使之不会产生额外的成本
    val arry = arrayOf(1,2,3,5)
    arry[0] = arry[0] * 2 //并不会调用 get或者 set方法。而是直接下标查找

    val kotlinStrArray: Array<String> = arrayOf("a", "c", "d")
//    myArray.testVarArgs(kotlinStrArray)//这样是不行的， 类型匹配异常
    myArray.testVarArgs(*kotlinStrArray)// spread operation 将数组展开


    /**
     * 关于Kotlin调用 Java有 check exception 检查异常的
     */
    val myExcetion = MyException()
//    myExcetion.testMethod()// 编译上没有报错，证明 Kotlin 没有 check exception,只有运行时 异常，运行时，此处肯定会抛出异常，但语法上并无提示异常


    val clazz = MyException::class.java

    println(clazz)//

    println(MyException().javaClass)


}