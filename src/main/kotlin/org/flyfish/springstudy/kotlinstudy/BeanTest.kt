package org.flyfish.springstudy.kotlinstudy

              //        primary 构造方法里
class Student (private val userName: String, private val age: Int, private val address: String) {
    fun printInfos() {
        println("userName: $userName, age:$age,address: $address")

    }

}

class Student2 (val userName: String = "fee"){
    //如果 一个类的 primary构造方法里的 所以参数都有默认值，那么 Kotlin 在 JVM编程上会自动增加一个无参数的构造方法，并且
    //该无参数的构造方法的会使用 primary构造方法中参数的默认值，这样做的目的是为了Kotlin和Spring更好的集成

}

fun main(args: Array<String>) {
    val student = Student2()

    println(student.userName)


}


