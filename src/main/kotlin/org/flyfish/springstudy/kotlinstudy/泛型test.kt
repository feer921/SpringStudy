package org.flyfish.springstudy.kotlinstudy

/**
 * 泛型：generics ,表示变量类型的参数化，(使用时再赋予类型)
 *
 * 协变：
 * List<Object>
 * List<String> 并不是 List<Object>的子类，
 * 这样定义时：List<? extends Object> ,List<String>才是 其子类
 * <? extends Object> 限定的是是 上限，会把所有的元素看成 Object
 * 上面称之为 协变
 *
 * List<? supper String> 限定的是下限，下限为String类型，称之为逆变
 *
 **/

class MyGeneric<T>(t: T){
    var variable: T = t
    init {
//        this.variable = t
    }

}
//out 只读 相当于 Java中的 ? extends(限定了上限)
//in 可写，相当于Java中的 ? super(限定了下限)
class MyClass2<out T,in M>(t: T, m: M){
    private var t: T
    private var m: M

    init {
        this.t = t
        this.m = m
    }

    fun get(): T = t



    fun set(m: M) {
        this.m = m
    }
}

fun myTest(myClass: MyClass2<String, Number>) {
    var myObject: MyClass2<Any, Int> = myClass

}
fun main(args: Array<String>) {
    var myGeneric: MyGeneric<String> = MyGeneric<String>("f")

    var myGeneric2 = MyGeneric("fe")

    println(myGeneric.variable)

    var myClass = MyClass2<String,Number>("abc", 1)

}

