package org.flyfish.springstudy.kotlinstudy

/**
 * 内部类(inner class)
 *
 * 关于嵌套类与内部类之间的区别与联系
 * 1、嵌套类：对应Java的静态内部类（即有static关键字修饰的内部类），只要在一个类的内部定义了另外一个类，那么这个类就叫做嵌套类，
 * 相应于Java当中有static关键字修饰的内部类；
 * 上面反过来说，Java里的静态内部类相当于Kotlin里的 嵌套类；
 *
 * 2、内部类：对应于Java中的非静态内部类（即没有static关键字修饰的内部类），在Kotlin中使用【inner】关键字在一个类的内部定义的另外一个类
 * 就叫做Kotlin的内部类，相当于Java当中没有使用static关键字修饰的内部类
 *
 */
class OutClass{
    private var name: String = "f"

    /**
     * 嵌套类，因为相当于Java的静态内部类，所以该类不可以访问外部类的成员
     */
    class InnerClass{
        fun tryTouchOutMem() {
//            this@OutClass.name//访问不了
        }
        fun innerMethod() = "nested class"
    }



}

class OutClass2{
    private var name = "out class2"

    inner class InnerClass{
        fun innerMethod() = this@OutClass2.name//引用外部类的成员
    }

    //局部嵌套类
    //定义在方法中
    fun partialInnerClass():String {
        class ClassInMethod{
            fun getName() = "class in method"

        }

        val clazzInMethod = ClassInMethod()
        return clazzInMethod.getName()
    }
}

fun main(args: Array<String>) {
    //嵌套类的使用
    println(OutClass.InnerClass().innerMethod())//用法和 Java的静态内部类相似


    //Kotlin内部类的使用

    println(OutClass2().InnerClass().innerMethod())

}