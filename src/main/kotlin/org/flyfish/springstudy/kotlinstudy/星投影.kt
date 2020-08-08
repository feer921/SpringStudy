package org.flyfish.springstudy.kotlinstudy

/**
 * 星投影 (star projection)
 * Star<out T>:如果 T 的上界是 TUpper，那么Star<*> 就相当于 Star<out T>
 * 这表示当 T的类型未知时，你可以从 Star<*>当中安全的读取 TUpper类型的值
 *
 * Star<in T>: Star<*>就相当于 Star<in Nothing>，这表示你无法向其中写入任何值
 * Star<T>,如果T的上界为 TUpper,那么Star<*>就相当于读取时的 Star<out TUpper>
 *     以及写入时的 Star<in Nothing>
 *
 */

class Star<out T>{
    val a: T = TODO()

}
class Star2<in T>{
    fun setValue(t: T) {

    }
}
class Star3<T>(private var t: T){
    fun setValue(t: T) {

    }
    fun getValue(): T {
        return this.t
    }
}
fun main() {
    val star1: Star<Number> = Star<Int>()
    val star2: Star<*> = star1//就相当于 Star<out Number>

    val star3: Star2<Int> = Star2<Number>()
    val star4: Star2<*> = star3
//    star4.setValue(3) // compile error

    val star5: Star3<String> = Star3<String>("hello")
    val star6:Star3<*> = star5
    star6.getValue()
//    star6.setValue("d")//compile error

    val list: MutableList<*> = mutableListOf("he", "ll")
    println(list[0])
//    list[0] = "dd" //compile error

    var myStorage: MyStorage<Int> = MyStorage(2)
    val myStorage2: MyStorage<Any> = myStorage//子类赋值给父类
    println(myStorage2.getValue())//--> 2

    myStorage2.setValue("dd")
    println(myStorage2.getValue())//--> "dd" //泛型擦除的原因导致允许

    val upperBoundsClass = UpperBoundsClass<List<String>>()



}
class MyStorage<out T>(private var t: T){
    fun getValue():T{
        return this.t
    }


    fun setValue(t: @UnsafeVariance/* 这样就允许了，但使用者自己保证 */ T) /*本身是会编译出错的，不能写入*/{
        this.t = t
    }
}

class UpperBoundsClass<T : List<Any>>{


}

/**
 * 泛型 有多个上界
 */
class UpperBoundsClass2<T> where T : Comparable<T>, T : Any {

}

