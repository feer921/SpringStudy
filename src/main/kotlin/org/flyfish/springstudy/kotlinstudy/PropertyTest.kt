package org.flyfish.springstudy.kotlinstudy

//Kotlin 的类成员(属性)
//backing field 支持字段(域)
//backing property

//Kotlin 要求非空类型的属性必须要在构造方法中初始化
//有时这种要求不太方便，比如可以通过依赖注入或者是单元测试情况下进行属性的赋值
//则可以通过 【lateinit】关键字，需要满足三个条件：
//1、【lateinit】只能用在类体中声明的 var属性上，不能用在primary method声明的属性上
//2、被声明的属性不能有自定义的get/set 方法
//3、属性类型不能为空，且不能为原生类型(eg.:Int)
class ThePerson(address: String, name: String){
    //定义了一个不可变属性
//1、    val age = 20
//2、    val age: Int = 20
//3、
    val age :Int
        get() = 20//get()方法体内只有一行代码，则省略{}并且 直接返回值20赋予该方法

    var address: String = address
        get() {
            println("getAddress() invoke...")
            return field //解释：外部调用 thePerson.address时，实际上是调用了 getAddress()方法，Kotlin使用 field来返回该变量的真实值
        }
        set(value) {
            println("setAddress() invoke...")
            field = value
        }


    lateinit var nickName: String

    fun init() {
        nickName = "fee"
    }



}


fun main(args: Array<String>) {
    var person = ThePerson("shanghai","zhangsan")

    println(person.age)


}