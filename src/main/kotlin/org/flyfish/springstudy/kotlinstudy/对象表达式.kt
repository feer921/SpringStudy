package org.flyfish.springstudy.kotlinstudy

/**
 * 对象表达式(object expression)
 *
 *  Java当中 匿名内部内在很多场景下都得到了大量使用
 *
 *  Kotlin的对象表达式就是为了解决Java的匿名内部类的缺陷而产生的
*/

/**
 * 匿名内部类：
 * 1、匿名内部类是没有名字的类
 * 2、匿名内部类一定是继承了某个父类，或者是实现了某个接口
 * 3、Java运行时会把匿名内部类当作它所实现的接口或者所继承的父类来看待
 *
 */

/**
 * 对象表达式的书写格式：
 * object [:若干个父类型,中间用','逗号分隔]{}  []表示可以没有的情况
 *
 * 注意事项：
 * 匿名对象(对象表达式) 只能在局部变量范围内或是被 private修饰的成员变量范围内才能被识别出其真正的类型。
 * 如果将匿名对象当做一个 public方法的的返回类型或者是public属性的类型，那么该方法或者是属性的真正类型就是该匿名对象所声明的父类型
 * ，如果没有声明任何父类型，那么其类型就是 Any，在这种情况下，匿名对象中所声明的任何成员都是无法访问的。
 */
class OwnerObjectExpressionClass{
    /*public 如果被public 修饰，则匿名对象中所声明的任何成员都访问不到*/
     val myObjects = object {
        fun outPut() {
            println("outPut invoked")
        }
        var name = "object expression"

    }

    fun test() {
        println(myObjects.javaClass)//OwnerObjectExpressionClass$myObjects$1
//        myObjects.outPut()
//        println(myObjects.name)

    }

}

class OwnerObjectExpressionClass2{
    private fun method1() = object {//对象表达式作为方法的返回类型
        val str = "hello"

    }
    internal fun method2() = object {
        val str = "world"

    }
    fun test() {
        println(method1().str)
//        method2().str; //访问不到
    }
}

interface MyInterface{
    fun myPrint(i: Int)

}
abstract class MyAbstractClass{
    abstract val age: Int

    abstract fun printAbstractClassInfo()

}

fun main(args: Array<String>) {
//    在局部变量范围内
    var localVar = 100
    var myObject = object : MyInterface {
        override fun myPrint(i: Int) {
            println("i is $i")
            localVar = 1000//Kotlin的匿名内部类可以修改外部变量的值，而Java的不能(只能访问并且需要为final修饰)

        }

    }

    myObject.myPrint(100)

    //不使用任何父类和接口
    var myObject2 = object {
        init {
            println("初始化代码块执行")
        }

        var myProperty = "hello world"

        fun myMethod() = "myMethod()"

    }
    println(myObject2.myProperty)
    myObject2.myMethod()

    println("--------------")

    //Kotlin中的匿名内部类可以有多继承和实现
    var myObject3 = object :MyInterface, MyAbstractClass() {
        override fun myPrint(i: Int) {
            println("i的值是$i")
        }

        override val age: Int
            get() = 30

        override fun printAbstractClassInfo() {

        }
    }
    println("**************")

    var owner1 = OwnerObjectExpressionClass()
    owner1.test()
}