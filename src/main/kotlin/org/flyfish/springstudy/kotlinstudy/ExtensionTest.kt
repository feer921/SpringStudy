package org.flyfish.springstudy.kotlinstudy

//扩展属性、方法

/**
 * 扩展函数的解析是静态的
 * 1、扩展本身并不会真正修改目标类，也就是说它并不会在目标类中插入新的属性或者方法
 * 2、扩展函数的解析是静态分发的，而不是动态的，即不支持多态，调用只取决于对象的声明类型(即和Java的多态区别，调用的方法取决于动态赋值的，而不是声明的类型决定)
 * 3、调用是由对象的声明决定的，而不是由对象的实际类型决定(即上面第2点，不支持多态)
 */
class Extension{
    fun add(a: Int, b: Int) = a + b

    fun subtract(a: Int, b: Int): Int = a - b


}
//给 Exception类扩展一个方法
fun Extension.multy(a: Int, b: Int) = a * b

fun main(args: Array<String>) {
    var extension = Extension()
    println(extension.add(1,2))
    println(extension.subtract(1,2))

    println("----------------------")

    println(extension.multy(1, 6))


    //3、调用是由对象的声明决定的，而不是由对象的实际类型决定(即上面第2点，不支持多态),测试
    myPring(AA())//则会调用 类AA的扩展 a()方法

    myPring(BB())//则仍会调用 类AA的扩展 a()方法，所以说 【调用是由对象的声明决定的】

    main2()
}

//3、调用是由对象的声明决定的，而不是由对象的实际类型决定(即上面第2点，不支持多态),举例
open class AA

class BB : AA() {

}

fun AA.a() {
    println("method in  a")

}

fun BB.a() {
    println("method in b")

}

fun myPring(aa: AA) {
    aa.a()
}

class CC{
    fun foo() {
        println(" member method foo()")
    }
}

fun CC.foo() {// 如果扩展方法和目标类的内的方法重名，则扩展没有意义，因为调用仍是调用类里的成员方法
    println(" foo() in extension")

}

fun CC.foo(i: Int) {//扩展的方法和目标类同名方法 形成重载

}

//还可以对可空类型进行扩展,如下面的 Any?
//如果下面的方法扩展了，则可以在任何可为null的类型，调用toString()时，都是空安全的
fun Any?.toString(): String {
    if (null == this) {
        return "";
    }
    return toString()
}

fun main2() {
    var s: String? = null
    s.toString()

    //扩展属性
    val extensionProperty = ExtensionProperty()
    println(extensionProperty.name)

}

//扩展属性

class ExtensionProperty

val ExtensionProperty.name: String
    get() = "hello"



