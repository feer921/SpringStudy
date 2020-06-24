package org.flyfish.springstudy.kotlinstudy

//伴生对象的扩展
//目前扩展的定义 都是在顶层内定义 的
class CompanionExtendTest{//这个类名如果和文件名同名，则会变为 Kotlin类
    companion object MyObj{

    }
}

//但如果 在类外写有其他偌，则本文件又会变为 Kotlin文件
fun CompanionExtendTest.MyObj.extendMethod() {

}

fun main(args: Array<String>) {
    //伴生对象的扩展方法的调用
    CompanionExtendTest.extendMethod();
}

//
