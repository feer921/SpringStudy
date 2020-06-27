package org.flyfish.springstudy.kotlinstudy


// 第26节 扩展的作用域
//扩展可以被定义在顶层文件中，也可以定义在类里

/**
 * 1、扩展函数所定义在的类实例叫做 【分发接收者】(dispatch receiver)
 * 2、扩展函数所扩展的那个类的实例叫做【扩展接收者】(extension receiver)
 * 3、当以上两个名字冲突时，【扩展接收者】优先级最高
 **/


class D{
    fun method() {
        println("DD method")

    }
}

class EE{
    fun method2() {
        println(" EE method2")

    }
    //然后在EE{}类里定义 一个 类 D{}的扩展方法,则EE{}类称之为 【分发接收者】，而D{}称之为 【扩展接收者】(就是说，我把你给扩展了，你接收吧)
    fun D.hello() {
        method()//可以使用 D{}类的方法(因为此时上下文为D类的实例)
        //也可以使用 【分发接收者】的方法
        method2()
    }

    fun world(dd: D) {
        dd.hello()//可以使用到 为D类扩展的方法hello()
    }

    //再为D类扩展方法 output()
    fun D.output() {
        //则当 方法体内使用 D类和EE类有相同方法时，也即 “当以上两个名字(【分发接收者】和【扩展接收者】)冲突时，【扩展接收者】优先级最高”
        println(toString())//-->即此时 toString()方法还是使用的 【扩展接收者】的方法
        //如果仍然是想调用 【分发接收者】的方法，则需要这样写：
        println(this@EE.toString())
    }

}

fun main(args: Array<String>) {
    var d = D()
//    d.output()// 这里就不能引用 在EE类里为 D类扩展的方法，因为不是顶层(包目前下)扩展 的方法



}