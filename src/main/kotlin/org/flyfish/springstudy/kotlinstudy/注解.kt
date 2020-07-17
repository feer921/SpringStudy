package org.flyfish.springstudy.kotlinstudy

import kotlin.reflect.KClass

/**
 * 注解：
 * Java中 以 @interface 来声明
 * Kotlin中以 [annotation] [class] 来声明
 * 需要通过 元注解[meta-annotation],来标示注释类的 使用
 */
fun main(args: Array<String>) {

}

//@Repeatable
@Retention(AnnotationRetention.SOURCE)//标注本注解的 保留 级别
@Target(AnnotationTarget.CLASS,
AnnotationTarget.FUNCTION,//方法上
AnnotationTarget.CONSTRUCTOR,//构造方法上
AnnotationTarget.VALUE_PARAMETER,//值参数上
AnnotationTarget.EXPRESSION//表达式上

        )//用该元注解 标识本 注解的使用范围
annotation class MyAnnotation

@MyAnnotation
class TestAnnotatio{

    @MyAnnotation/*方法上*/
    fun a(@MyAnnotation/*值参数上*/ a: Int): Int {

        return  (@MyAnnotation/* 表达式上*/ 10)
    }
}

class TestAnnotation @MyAnnotation constructor/* 如果构造方法有修饰，则不能省略[constructor]关键字 */(a: Int)/* primary constructor */{


}

/**
 * 注解也可以拥有构造方法，并且构造方法也可以接收参数
 * 注解构造方法所允许的参数类型：
 * 与Java原生类型所对应的类型(Int,Long...)
 * 字符串：String
 * classes (MyClass::class)
 * 枚举：enums
 * 其他的注解
 * 上述类型的数组类型
 *
 * Kotlin的注解参数是不允许 为可空类型的，因为JVM不支持以 [null] 的形式存储注解属性值的。
 * 如果某个注解用作 其他注解的参数，那么其名字前不需要以 [@] 字符开头，如[MyAnnotatino3]
 */
annotation class MyAnnotatino2(val name: String){


}
@MyAnnotatino2("myClass1")
class MyClass1{}


annotation class MyAnnotatino3(val name: String, val annotaion: MyAnnotatino2/* 构造方法中带 参数为注解类型 */){


}

@MyAnnotatino3("myClass3", MyAnnotatino2("myClass3")/* 该 */)
class MyClass3 {

}

/**
 * 如果需要将某个classw作为注解的参数，那么请使用 Kotlin class [KClass]为一个接口
 * Kotlin编译器会自动将其转换为Java Class
 * 这样Java代码就可以正常看到注解与参数了
 */

annotation class MyAnnotation4(val arg1: KClass<*>/* 将某个classw作为注解的参数 */, val arg2: KClass<out Any>) {

}
@MyAnnotation4(String::class,Int::class)
class TestAnnotaion


