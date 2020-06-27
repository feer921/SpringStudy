package org.flyfish.springstudy.kotlinstudy

//密封类
/**
 *  密封类 类似 枚举，但和枚举不同
 *  密封类本身是一个抽象类，可以有多个子类，并且直接子类 的定义 需要和密封类在同一个文件内
 *  但是直接子类的子类，可以不在同一个文件中
 *
 **/

sealed class Calculator

class Add : Calculator()

class Subtract:Calculator()

fun calculat(a: Int, b: Int, calculator: Calculator) = when (calculator) {
    is Add -> a + b
    is Subtract -> a - b


}
fun main(args: Array<String>) {

}