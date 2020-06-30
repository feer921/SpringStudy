package org.flyfish.springstudy.kotlinstudy

import kotlin.reflect.KProperty

/**
 * 委托之：
 * 二：属性委托(使用更为广泛)
 *
 * 有4种情况在实际开发中比较有用
 * 1、延迟属性
 * 2、可观测属性
 * 3、非空属性
 * 4、map属性
 */
class PropertyDelegate{//属性被委托的类，需要具备两个方法 get / set

    operator/** 需要使用 operator关键字修饰 **/ fun getValue(thisRef: Any?, property: KProperty<*>): String/** 返回值要和 委托的属性类型一致**/ {
        return "$thisRef ,your delegated property name is ${property.name}"
    }

//    operator fun setValue(myPropertyClass: MyPropertyClass, property: KProperty<*>, s: String) {
//        println("))))))))))))) $s") //,如果有这个 则会调用 方法参数最接近 委托类的方法
//
//    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: String) {
        println("$thisRef ,new value is $newValue")
    }


}

class MyPropertyClass{
    var str: String by/** 同样使用by关键字 将属性委托**/ PropertyDelegate()


}

fun main(args: Array<String>) {
    val myPropertyClass = MyPropertyClass()
    myPropertyClass.str = "hello world"//这行会调用 被委托类的 setValue方法
    println(myPropertyClass.str) //这行会调用 被委托类的 getValue 方法

}