package org.flyfish.springstudy.kotlinstudy

//Kotlin 里有的对象声明
// object declaration,使用 object关键字，表示创建出了一个对象，所以在使用该对象内的方法时，不用再 MyObject()构造出来，直接调用方法
object MyObject{
    fun methodInMyObject() {
        println("methodInMyObject()")
    }
}


fun main(args: Array<String>) {
    //有点类似于JAva的静态方法，但其实不是，
    MyObject.methodInMyObject()
    //调用伴生对象
    //一：
    println(MyTest.MyCompanionObj.a)
    MyTest.MyCompanionObj.methodOfMyCompanionObj()
    println("---------------------------")
    //二
    println(MyTest.a)
    MyTest.methodOfMyCompanionObj()//类似于静态方法，但是Kotlin没有静态方法




}

//--------------伴生对象

class MyTest{
    //Kotlin 在大多数情况下推荐是使用包级别的函数来作为静态方法
    //Kotlin 会将包级别的函数当做静态方法来看待
    //伴生对象，顾名思义即伴随MyTest对象而生，Kotlin使用伴生对象来实现Java中的静态属性、方法,
    //并且 每个 class内只能有一个伴生对象
    //1)转换成 JVM的类时，即为MyTest的内部类,即 class: 包名.MyTest$MyCompanionObj
    //2)伴生对象会被编译成 静态内部类
    //注意：虽然伴生对象的成员(包括属性、方法)看起来像Java中的静态成员，但在运行期，他们依旧是真实对象的实例成员
    //在Jvm上，可以将伴生对象的成员真正生成为类的静态方法与属性，这是通过@JvmStatic注解来实现的
    companion object MyCompanionObj/** 该名字是可以省略的，如果省略，Kotlin会给一个默认的名字，为【Companion】 **/{
        var a: Int = 100
//        @JvmStatic //加上这个注解后，methodOfMyCompanionObj()则会编译成 MyTest的静态方法
        fun methodOfMyCompanionObj() {
            println("methodOfMyCompanionObj()")
        }
    }
    //Only one companion object is allowed per class
//    companion object MyOjb2{
//
//    }
}