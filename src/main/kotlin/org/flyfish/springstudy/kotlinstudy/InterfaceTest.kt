package org.flyfish.springstudy.kotlinstudy

interface A{
    fun method(){
        //和Java8一样，接口中的方法可以有实现
        println("method of A")
    }

}

open class B{
   open fun method() {
       println("method of B ")
    }
}

class C : B(),A {
    override fun method() {
        super<A>.method()
        super<B>.method()

    }

}


open class AClass{
    open fun method() {

    }
}

abstract class BaseClass : AClass(){
    //抽象类可以 重写父类的方法并且 把它抽象化
   abstract override fun method()
}