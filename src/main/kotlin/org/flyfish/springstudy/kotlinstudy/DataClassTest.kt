package org.flyfish.springstudy.kotlinstudy

//数据类 -- data class

//lombok 的框架功能类似,为定义的类自动生成 get,set方法

/**
 * 数据类 需要满足如下要求：
 * 1、所定义的class的主构造方法（primary method)至少要有一个参数
 * 2、在主构造方法内所有的参数需要被标记为 var或者 val,因为这些参数都将是类的属性
 * 3、data class 数据类不能是抽象的、open的、sealed的以及inner的
 *
 * 对于 数据类，编译器会自动生成以下内容：
 * 1、equals/hashCode方法
 * 2、toString()方法，形式为 Person(name=...,age=...,address=...)
 * 3、针对属性的componentN(N=1，2，3...)方法,并且是按照属性声明的顺序来生成的
 *
 * 关于数据类成员的继承要点:
 * 1、如果数据类中显示的定义了equals/toString/hashCode方法，或者是在数据类的父类中将这些方法声明了
 * final,那么这些方法就不会生成了，转而使用已有的。
 * 2、如果父类拥有componentN方法，并且是open的以及返回兼容的类型，那么编译器就会在数据类中生成相应的componentN方法，
 * 并且重写父类的这些方法；如果父类的这些方法由于不兼容的签名或者被定义为final的，那么编译器就会报错。
 * 3、在数据类中显示的提供 componentN方法以及copy方法实现是不允许的
 *
 *
 * 解构声明
 * 编译器自动为 数据类生成 的componentN方法，的作用就是用来 解构
 * 数据类的 primary方法有多少个参数，就会依定义次序生成，component1,component2,component3...
 * 这些方法的返回值就是对应属性的值，componentN方法是用来实现解构声明的
 */

open class Anim(open val name:String){

}
data class Person(override val name: String, var age: Int, val address: String):Anim(name = name){
}

fun main(args: Array<String>) {
    var p = Person("fee",22,"beijing")

    println(p)

    var p2 = p.copy(address = "shenzheng")

    //componentN方法的解构用法
    var (name,age,address) = p //这样就使用对象 p的对应的component方法的返回值来给 前面的赋值



}
