package org.flyfish.springstudy.kotlinstudy

import java.util.*
import kotlin.properties.Delegates
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
//委托
//1、延迟属性 :指的是属性在第一次被访问的时候才会计算，之后则会将之前的计算缓存起来供后续调用
/**
 * lazy函数的第一个线程模式说明：
 * 1)、SYNCHRONIZED：默认情况下，延迟属性的计算(初始化)是同步的：值只会在一个线程中得到计算，所有线程都会使用相同的一个结果。即线程安全
 * 2)、PUBLICATION： 如果不需要初始化的委托的同步，这样多个线程可以同时执行。
 * 3)、NONE： 如果确定初始化操作只会在一个线程中执行，这样会减少线程安全方面的开销
 */
val myLazyValue2: Int by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {


    90
}
val myLazyValue: Int by lazy/** lazy是一个函数，返回的是Lazy<T>,其函数的参数也是一个函数，所以可以放在外面 **/ {
    println("my lazy vallue init")
    39
}


//非空属性 委托
//这个比较简单，个人觉得，还不如
//var address:String = ""
//notNull委托适用于那些无法在初始化阶段就确定属性值的场合
var address: String by Delegates.notNull<String>()//使用非空属性委托后，在使用该变量时，一定要先赋值，再使用，不然抛异常


//2、可观测属性 委托， 使用 Delegates.observable()方法的返回对象

fun <T> onChange(property: KProperty<*>, oldValue: T, newValue: T) {
    println("$property,  oldValue: $oldValue, newValue: $newValue")
}
//var age: Int by Delegates.observable(20, ::onChange)
/**
 * [Delegates.observable] 接收两个参数：变量的初始值和处理器【我觉得理解为变更回调更合适】。
 * 【处理器】在我们每次对属性赋值进得到调用（在赋值完成后调用）
 * 【处理器】本身接收3个参数：被赋值的属性本身；旧的属性值；新的属性值
 *
 * [Delegates.vetoable] 的调用时机与[Delegates.observable]相反，它是在对属性赋值之前被调用，根据[Delegates.vetoable]的返回结果是ture还是false,来
 * 决定是否真正对属性赋值
 */
var age: Int by Delegates.observable(20){
    property, oldValue, newValue ->

}

var age2: Int by Delegates.vetoable(90){
    property, oldValue, newValue ->
    when {
        newValue >= oldValue -> true
        else -> false
    }
}

/**
 * 委托 之属性委托之 4、 map委托:
 * 将属性存储到map中
 * 一种常见的应用场景是将属性值存储到map当中
 * 这通常出现在Json解析 或是一些动态行为。
 * 在这种情况中，  你可以将map实例作为委托，作为类中属性的委托
 * map中 key的名字要与类中属性的名字保持一致
 *
 * <p>
 *     属性委托转换规则(字节码层面)：
 *     对于每个委托属性来说，Kotlin编译器在底层会生成一个辅助的属性，然后将对原有属性的访问委托给这个辅助属性。
 *     比如说，对于属性prop来说，Kotlin编译器所生成的隐含的属性名为prop$delegate，然后对原有的prop属性的访问器(setValue/getValue)的访问都
 *     只是委托给了这个额外的，Kotlin编译器所生成的辅助属性,比如prop属性的setValue()则会转换调用prop$delegate的setValue
 *
 * </p>
 */
class MapProperty(property: Map<String, Any?>){
    val name: String by property
    val address: String by property
    val age: Int by property
    val birthday: Date by property

}

fun testMapDelegate() {
    val mapProperty = MapProperty(mapOf(
            "name" to "fee",
            "address" to "beijing",
            "age" to 1,
            "birthiday" to Date()
    ))
}
fun main(args: Array<String>) {
//    val myPropertyClass = MyPropertyClass()
//    myPropertyClass.str = "hello world"//这行会调用 被委托类的 setValue方法
//    println(myPropertyClass.str) //这行会调用 被委托类的 getValue 方法

    //下面虽然调用两次，但只会有一次 "my lazy vallue init"输出，表明只会初始化一次
//    println(myLazyValue)
//    println(myLazyValue)

    //非空属性 委托 一定要先赋值，再使用，不然抛异常
//    address = "ddd"
//    println(address)

    println("-------------------")

    println("args is $age")

    val str = "index_story_v6.html"
    val index1 = str.indexOf("_")
    val index2 = str.lastIndexOf("_")

    println("index1 = $index1, index2 = $index2, ")

    //          //左闭、右开
    println(str.substring(index1+1, index2))
    testMapDelegate()

}