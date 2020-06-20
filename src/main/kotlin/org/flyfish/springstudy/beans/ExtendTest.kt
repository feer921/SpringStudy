package org.flyfish.springstudy.beans

/**
 * Kotlin 默认所有的类都是 final 类型的，不能被继承
 * 如果要被继承，则需要对应的 class增加关键字 open
 */


open /** **/class Parent(userName: String, age: Int){

}


class Child(userName: String, age: Int) : Parent(userName, age) {

}

open class Parent2(userName: String)

/**
 * 如果一个 类 没有primary 构造方法(头部构造方法)
 * 但继承了有 primary构造的父类，则需要，在这个类的 secondary构造方法使用 super调用父类的构造方法
 */
class child2 : Parent2 {

    constructor() : super("f") {

    }


}


//////////////////////////////// override test


open class Fruit{
    open/** 使用open关键字修饰才能被重写**/ fun name() {
        println("fruit")
    }
    fun price() {
        println("1.99元")
    }
}

class Apple : Fruit{
    constructor() : super()

    //如果子类 需要重写 父类的方法，则需要父类的对应方法也是 open关键字修饰的
    override fun name() {
        println("apple")
    }

}

open class Orange : Fruit() {
    //如果重写父类的方法前再加 final关键字，则其子类不能再重写该方法了
    final override fun name() {
        super.name()
    }
}

///------------------------------- 属性的重写-----------------------

open class MyParent{
    //声明 的属性默认也是 final的，不能被直接重写
    //如果属性也要能被重写，需要 在属性前增加 open 关键字
    open val name = "parent"

}

class Child1 : MyParent() {
    override val name = "child1"


}

class Child2(override val name: String) : MyParent() {

}