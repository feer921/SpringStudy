package org.flyfish.springstudy.kotlinstudy

/**
 * Kotlin 声明处协变（也可以使用处协变）；Java：使用处协变
 * Kotlin中的out关键字叫做 variance annotation,因为它是在类型参数()声明处所指定的，
 * 因此我们称之为声明处协变（declaration-site variance）
 * 对于 Java来说是使用处协变(use-site variance),其中类型通配符使得类型协变成为可能
 */

/**
 * producer --> output-->out
 */
interface Producer<out/** 协变类型，只能用于方法的返回类型 **/ T>{

    fun produce(): T

//    fun setT(t: T)//compile error
}

/**
 * consumer --> input -->in
 */
interface Consumer<in/** 逆变，只写不可读**/ T>{

    fun consume(t: T)

}

/**
 * 不变
 */
interface ProducerConsumer<T>{
    fun produce():T
    fun consume(t: T)

}

open class Fruit1

open class Apple1 : Fruit1() {

}

class ApplePear : Apple1() {

}
class FruitProducer : Producer<Fruit1> {
    override fun produce(): Fruit1 {
        println("produce fruit..")
        return Fruit1();
    }
}
class AppProducer : Producer<Apple1> {
    override fun produce(): Apple1 {
        println("produce apple")
        return Apple1()

    }
}

class ApplePearProducer : Producer<ApplePear> {
    override fun produce(): ApplePear {
        println("produce apple pear")
        return ApplePear()
    }
}

fun main(args: Array<String>) {
    /**
     * 对于 [out] 泛型来说，我们可以将子类型对象赋值给父类型
     * 这里说的 子类型说的是 <T>,上声明 的类型
     */
    val producer1: Producer<Fruit1> = FruitProducer()

    /**
     * <Fruit1>   =  <Apple1>
     */
    val producer2: Producer<Fruit1> = AppProducer()//AppProducer:Producer<Apple1>
    //producer2 在调用 produce()方法时，本来是要返回 Fruit1对象的，但是由于指向了 AppProducer
    //所以返回的是 Apple1(),根据多态，则是可以的
    //因此即形成了 协变
    producer2.produce()

    val producer3: Producer<Fruit1> = ApplePearProducer()


    val consumer1: Consumer<ApplePear> = Human()

    val consumer2: Consumer<ApplePear> = Man()

    consumer2.consume(ApplePear())//当调用这里时,consumer2只能传入 ApplePear(),而consumer2实际要求的是consume(t: Apple1),
    //而因为多态 ApplePear确实是 Apple1，所以可以
    //因此形成 【逆变】
    val consumer3: Consumer<ApplePear> = Boy()



}
class Human : Consumer<Fruit1> {
    override fun consume(t: Fruit1) {
        println("human consume Fruit...")

    }
}

class Man : Consumer<Apple1> {
    override fun consume(t: Apple1) {
        println("man consume apple")
    }
}

class Boy : Consumer<ApplePear> {
    override fun consume(t: ApplePear) {
        println("boy consume apple pear")
    }
}

