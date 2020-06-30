package org.flyfish.springstudy.kotlinstudy

enum class SEASON{
    SPRING,
    SUMMER,
    AUTUMN,
    WINTER;//[这里的分号可以省略]

}

//带构造方法的
enum class SEASON2(val tempr: Int){
    SPRING(10),
    SUMMER(20),
    AUTUMN(23),
    WINTER(-1)
}

//枚举内中带方法的
enum class Season3{
    SPRING {
        override fun getSeason(): Season3 = SPRING
    },
    SUMMER{
        override fun getSeason(): Season3 = SUMMER
    },
    ;//该分号不能省略，定义 完穷举 枚举对象后

    abstract fun getSeason(): Season3

}

fun main(args: Array<String>) {
    val seasons = SEASON.values()
//    seasons.forEach { oneSeason: SEASON -> println(oneSeason)}
    seasons.forEach {println(it)}

    println("--------------")

    //根据枚举对象的名称获取枚举对象

    val spring = SEASON.valueOf("SPRING")


}