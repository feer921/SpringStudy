package org.flyfish.springstudy.kotlinstudy

/**
 * Kotlin是严格区分可变集合 与不可变集合
 * 要清楚一点的是：需要区分开可变集合的只读视图(类似数据库中的view) 与实际上真正的不可变集合
 */
fun main(args: Array<String>) {
    val stringList: MutableList<String>/* 可变List */ = mutableListOf("hello", "world", "hello world")

    val readOnlyView: List<String> = stringList;


    println(stringList)
    println("then add one")

    stringList.add("welcome")
    println(readOnlyView) //这里肯定会有 "welcome" ,因为 readOnlyView 指向就是 stringList的引用

    //但
//    readOnlyView.clear()//因为 List 是不可变集合,所以不具备可变方法

    val stringsSet: HashSet<String> = hashSetOf("a", "b", "c", "c")

    //只读类型是 协变的，因为它只用于从集合中获取数据，而不会修改集合中的数据
    val s = listOf ("a","b")

    val s2:List<Any> = s

    //快照

    val item1 = mutableListOf<String>("a", "c", "d")
    val item2 = item1.toList()//集合的快照，后生成该集合的数据，即为此刻的数据内容
    item1.add("de")

    println(item1)//a,c,d,de

    println(item2)//a,c,d


    /**
     * Range
     */
    val i = 4
    if (i in 1..5/* 左、右为闭区间 */) {
        println(i)
    }

    println("----------")

    for (a in 1..4) {
        println(a)
    }


    for (a in 4..1) {// 等价
//        for(a = 4; a<=1,a++){
//
//        }
        //所以是没有输出的
    }

    for (a in 4 downTo 1) {
        //等价于
//        for(a = 4,a<=1; a--){
//
//        }


    }

    for (a in 6 downTo 1 step/* 该值为正数 */ 2){

    }

}