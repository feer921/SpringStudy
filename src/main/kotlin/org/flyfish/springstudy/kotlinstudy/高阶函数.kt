package org.flyfish.springstudy.kotlinstudy



/**
 * 高阶函数 (high-order function)与 lambda
 * Lambda 表达式格式要求：
 * 1、一个Lambda表达式总是被一对花括号{}包围
 * 2、其参数（如果有的话）位于 -> 之前（参数类型是可以省略掉的，如果可以推断出来）
 * 3、执行体位于 -> 之后
 * 在Kotlin中如果一个函数的最后一个参数是个函数，那么可以将Lambda表达式作为实参传递进去，
 * 并且可以在调用时方法圆括号()外去使用
 *
 *
 */

val multyply: (Int, Int) -> Int/* 这里是变量的类型，表示为该变量为一个函数类型 类似于val str:String 中的String */ = { a, b -> a * b }

val subtract/* 这里表示省略了声明变量的类型，而由后面推断出来 */ = {a:Int,b : Int -> a - b}

val myAction = { println("Hello world")}

//上面完整的声明为：//无参数，且返回为Unit
//val myAction2: () -> Unit = {println("Hello world")}

val mayReturnNull: (Int, Int) -> Int? = { a, b -> a + b }

//或者
val mayReturnNull2: (Int, Int) -> Int? = { _, _/* 当前参数在执行体里不使用时，可以使用_占位 */ -> null }

//表示该 函数变量本身就可为 null
val functionMayNull: ((Int, Int) -> Int?) ? = null


fun String.filter(condition: (Char) -> Boolean): String {
    var strBuilder: StringBuilder? = null
    toCharArray().forEach {
        if (condition(it)) {
            if (strBuilder == null) {
                strBuilder = StringBuilder()
            }
            strBuilder!!.append(it)
        }
    }
    return strBuilder.toString()//这里调用了以前 扩展的方法,呵呵！！！
}


/**
 * lambda
 * 在默认情况下，lambda 表达式中最后一个表达式的值会隐式作为该 lambda表达式的返回值
 * ：{a+b},即 a+b 这个表达式即为 lambda表达式的返回值
 * 我们也可以通过全限定的 return 语法来显式从
 */

fun main(args: Array<String>) {
    val filterStr = "abd334zy90".filter {
        it.isLetter()
    }
    println(filterStr)

    val strings = arrayOf("hello", "world", "bye")
    strings.filter {
        val mayFilter = it.length > 4
        mayFilter// 如果这是最后一行，则隐式的为 lambda的返回值
    }

    strings.filter {
        val mayFilter = it.length > 5
//        return mayFilter //如果这样 return ，则不正确 Type mismatch.
//        Required:
//        Unit
//        Found:
//        Boolean
        return@filter mayFilter// 全限定的 return 语法,   //返回 到 filter方法名后
    }
}