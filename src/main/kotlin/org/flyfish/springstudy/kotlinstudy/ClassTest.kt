package org.flyfish.springstudy.kotlinstudy

class EmptyClass //如果一个类没有类体，则{}也可以省略


class MyClass  constructor(username: String) {//primary constructor()
    // constructor()为主构造方法
//    private val username: String = username.toUpperCase()

    private var userName: String
    private var age: Int
    private var address: String

    init {
        //初始化代码块
        println("in init code block username = $username")
        userName = username;
        age = 0
        address = ""
    }

    //secondary constructor() 一定要间接或者，直接调用 primary constructor()
    constructor(username: String, age: Int) : this(username) {
        this.age = age
        this.userName = "2222"
        println(" in 2 params 构造方法  userName = $userName")
    }


    constructor(username: String, age: Int, address: String) : this(username){
        this.userName = userName

    }


    override fun toString(): String {
        return "MyClass(userName='$userName', age=$age, address='$address')"
    }


}

fun main(args: Array<String>) {
    val myClass1 = MyClass("dd")

    val myClass = MyClass("ff", 20)
    val myClass2 = MyClass("ffff", 22, "dd")

    println("after 构造出 对象 $myClass")


}