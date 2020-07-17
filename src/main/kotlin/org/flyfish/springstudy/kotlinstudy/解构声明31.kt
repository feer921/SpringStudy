package org.flyfish.springstudy.kotlinstudy


/**
 * 解构声明
 * 个人理解：一种通过解析对象构造的方法简便创建出已有对象的一种声明
 */

data class MyServerResult(val resultDesc: String, val statusCode: Int)

fun respMyResult(): MyServerResult {
    return MyServerResult("Success", 200)
}

fun myResult2(): Pair<String, Int> {
    return Pair("Success", 200)//相当于一个方法返回两个值
}
fun main(args: Array<String>) {
    //正常的变量声明对比
    val myResult = respMyResult()
    println(myResult.resultDesc)
    //解构声明，首先是一种语法上的声明
    val (result, status)/* 这里就是解构声明 */ = respMyResult()
    //则可以
    println(result)//直接取出 result 的值


    val (result2, status2 /* 不能和上面重复了*/) = myResult2()


    println("-----------")

    //解构在Map上的使用
    val map = mapOf<String, String>("a" to "aa", "b" to "bb", "c" to "cc")

    for ((key, value)/* 解构map的元素 */ in map) {
        println("key $key, value $value")

    }

    println("----------------------")
    //mapValues(transform: (Map.Entry<K, V>) -> R): Map<K, R>
    map.mapValues { entry -> "${entry.value} hello" }.forEach { println(it) }

    println("--->")
                        //R
    val map2:Map<String,Int> = map.mapValues {
        (key,value)/* 这里才是解构声明 */ ->
        value.toInt()//R
    }
    map2.forEach{
        println(it)
    }

    map.mapValues { (_/* 未使用到的参数用"_"代替 */,value)-> "$value + world" }.forEach { println(it) }

    println("**************")

    /**
     * Kotlin 允许我们为 解构声明整体指定类型，也可以为具体的某一个 component指定类型
     *
     */
    map.mapValues { (_,value/* 某个component */ /*:String*/): Map.Entry<String,String> -> {"$value people"} }.forEach { println(it) }


}