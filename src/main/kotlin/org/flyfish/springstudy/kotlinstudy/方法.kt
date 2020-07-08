package org.flyfish.springstudy.kotlinstudy

/**
 * 默认参数，(default argument)
 */
fun addTest(a: Int = 0, b: Int = 1) = print(a - b)

/**
 * (一)如果一个默认参数位于其他无默认值的参数前面，那么默认值(即如果使用该默认值的意思)只能通过在调用函数时使用具名参数(其他参数)的方式来使用。
 * eg.: addTest2(b = 3),就是说在 需要使用到有默认值的参数的默认值时，其他参数需要具名传参
 * 如果这样：addTest2(3,5),表示没有使用到 a这个参数的默认值
 */
fun addTest2(a: Int = 1, b: Int) = a + b


/**
 * 如果函数的最后一个参数是lambda表达式 ，而且在调用时是位于圆括号之外，
 * 那么就可以(仍然要使用该函数的前面默认参数值时)不指定lambda表达式的具名参数名(即打破上面一的规则)
 */
fun methd3(a: Int = 1, b: Int = 2, compute: (x: Int, y: Int) -> Unit) {
    compute(a,b)
}

/**
 * 具名参数
 * 在调用函数时，函数参数是可以具名的 foo(a=1,b=2)
 * 当一个函数有大量参数或是一些参数拥有默认值时，这种调用方式是比较方便的/直观
 *
 */
fun method4(a: Int, b: Int = 1, c: Int = 2, d: Int) = print(a + b + c + d)

/**
 * 在调用函数时，如果同时使用了位置参数与具名参数，那么所有的位置参数都必须要位于第一个具名参数之前
 * 比如说：foo(1,x=2)这样是允许的；不过如果为foo(x=2,1)是不允许的
 */
fun testLamdaMethod() {
    methd3(1, 3, ::addTest)//未使用 methd3()声明的默认方法，并且最后一个函数参数，使用既有的方法
    methd3(3,5,{a,b -> a * b})//使用 methd3()方法的完整签名
    methd3 /*() lamda片段移出 ()外，并且使用了该方法声明的默认的参数值*/{ x, y -> x * y }


}
/**
 * 方法重写
 * 对于重写的方法来说，子类所拥有的重写方法会使用与父类相同的默认参数值；
 * 在重写一个拥有默认参数值的方法时，方法签名中必须要将默认参数值省略掉。
 */

open class AFatherClass{
    open fun method1(a: Int, b: Int = 5) = a + b

}

class AChildClass : AFatherClass(){
    override fun method1(a: Int, b: Int /* = 3 并且需要省略，不能再赋值默认值*//* 这里即会也有默认值为父类声明的 */) = a + b
}

/**
 * 可变参数方法,可变参数与Java里的以"..."声明的类似，本质上都是数组
 * 但是Java的可以直接传入一个数组，而Kotlin不行（提示类型异常）
 * 但Kotlin可以在数组的引用上 前缀"*"[spread operator 分散运算符],
 */

//在Kotlin当中调用Java方法时不能使用具名参数语法，因为Java字节码并不总是会保留方法的参数名信息，参数名信息都没有，也就不存在具名传参了
fun varargMethod(vararg string: String) {
    println(string.javaClass)
    string.forEach { println(it) }

}
fun main(args: Array<String>) {
    addTest()//参数不传，即参数都使用 方法声明参数的默认值
    addTest(1)//只传一个时，参数为依序传给对应的参数
    addTest(b = 3)//参数具名，指定某个参数
    addTest(3, 2)

    testLamdaMethod()

    println("---可变参数------")
    varargMethod("a", "b", "c")
//    varargMethod(string = "a")//之前版本的Kotlin可以？？？
    //                    前缀"*"[spread operator 分散运算符]
    varargMethod(string = *arrayOf("ab", "cd"))//可以具名
    val array = arrayOf("a")
    varargMethod(*array)

}


//-----------------函数定义说明------------------------

fun myPrint(name: String): Unit /* Unit为 object声明的对象(注意已经是对象了) 可省略*/ {
    println(name)

//    return//可以这样，但啰嗦
//    return Unit //直接返回  Unit对象

}

/**
 * 单表达式函数，函数的返回值直接是一个表达式，并且该函数的返回值类型可以通过该表达式推断出来，那么返回类型是可以省略的
 */
fun addAb(a: Int, b: Int) = a + b

/**
 * 显示返回类型
 * 拥有方法体的函数必须要显式指定返回类型，除非函数返回Unit，这里返回类型就可以省略掉
 * Kotlin并不会推断拥有块体的函数的返回类型，因为这种函数可能拥有非常复杂的控制流程，
 * 返回类型对于阅读代码的人来说就不是那么明显了(有时对于编译器来说亦如此)
 */
//fun a() :Int{
//    val a = 1
//    a
//    //需要显式指定返回类型
//    return 1
//}

/**
 * 一个方法中只允许有一个 vararg可变参数的声明，同Java，通常作为最后一个参数声明。
 * 但Kotlin可以把可变参数不声明在最后一个参数位置，此时在可变参数后面声明的参数，在调用时需要使用具名参数形式传参
 *
 */
fun test() {
//    varargMethod2("a", "b", 2)//这样是不行的
    varargMethod2("a", "b", age = 3)//这样正确

}
fun varargMethod2(vararg str: String, age: Int) {

}

/**
 * 中缀 符号(infix notation)
 * 函数还可以通过中缀符号形式来调用，需要满足如下3点：
 * 1、该函数是成员函数或是扩展函数
 * 2、该函数拥有单个参数
 * 3、声明时使用[infix]关键字
 */

class InfixTest(private var a: Int){
    infix fun add(b: Int) = this.a + b
}

/**
 * 内联函数 inline function
 *
 * 使用 [inline]关键字修饰 ，当其他地方调用内联函数时，运行时会把该内联函数方法体内的信息 复制到调用处，
 * 在字节码里看不到该内联函数
 * 缺点：
 * 导致字节码比较大
 * 有一定的性能损耗
 */
inline fun inlineTest(a: Int, b: Int) = a + b

fun testInfixMethod() {
    val infixTest = InfixTest(3)
    println(infixTest.add(5))//传统调用方式
    println(infixTest add 5)//中缀符号形式调用: 对象实例 空格 方法名 参数

    //调用内联函数处，会把 方法体 {a,b,a+b}信息复制一份到此处
    println(inlineTest(1, 2))
}



