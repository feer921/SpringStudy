//@file: JvmName("HelloWorld")// 如果多个文件，都使用这种自定义编译后的class名字，如果出现相同自定义名字的时，则编译会报错
                //但Kotlin提供了，可以让所自定义的class名字共存的方案
//@file: JvmMultifileClass //每个Kotlin文件中加上这个注解，即可以 让所自定义的class名字的文件共存，字节码中是采用合并的方式

package org.flyfish.springstudy.kotlinstudy

/**
 * 属性(properties)
 * 一个Kotlin属性会被编译为3个Java元素
 * 1、一个getter方法；
 * 2、一个setter方法；
 * 3、一个私有的字段(field)，其名字与Kotlin的属性名一样
 *
 * 如果Kotlin的属性名以[is]开头，那么命名约定会发生一些变化：
 * eg.: [isName]不一定是 Boolean类型的属性
 * 1、getter方法名与属性名一致： eg.: [isName]
 * 2、setter方法名为把[is]替换为 [set], eg.:[setName]
 * 3、字段(field),其名字仍然与属性名一致，在编译中为: private java.lang.String [isName]
 *
 * 这种规则适用于任何类型，而不单单是Boolean类型
 *
 */
class Test{

    /**
     * 使用[@JvmField]注解对Kotlin中的属性进行标注时，表示它是一个实例字段(instance field),Kotlin编译器在处理的时候
     * 将不会给这个字段生成setter/getter
     * 外部使用直接可以 实例对象.该属性的方式
     * 但是很多情况下，没必要这么做
     */
    @JvmField
    var name: String = "test"

}

/**
 * package 下定义的属性和方法，编译后为 本文件的Java class: [Java与KotlinKt.class]
 * 的静态属性与静态方法
 */
var name: String = "hello"
fun testA() {

}

/**
 * 下面这两个扩展方法会报异常，异常为方法签名冲突：
 * Platform declaration clash: The following declarations
 * have the same JVM signature (myFilter(Ljava/util/List;)Ljava/util/List;):
 * 看 myFilter(List arg) 则说明Kotlin的扩展方法在Jvm上其实是被扩展的对象实例当作所扩展的方法参数传入
 * 另可以说明，虽然List<T> 是泛型，但其实JVM内不存储泛型信息(为假泛型) 字节码内 泛型所指的类型 会被 类型擦除,而是使用强制类型转换来实现
 * 所以类型一擦除，下面两个方法即为 签名一致
 * 但Kotlin还是可以通过 注解来解决这个问题 [@JvmName] 相当于重命名了
 */
@JvmName("myFilter2")
fun List<String>.myFilter():List<String>{
    return listOf("a", "b")
}
fun List<Int>.myFilter():List<Int>{
    return listOf(1, 2)
}

/**
 * 由于 Java语法里并没有 参数默认值的设计，
 * 所以如果Kotlin的类的构造方法或者方法中的参数有默认值，那么Java是无法使用到Kotlin中所定义的默认值
 * Kotlin为了兼容这一问题，可以使用注解 [@JvmOverloads],使用该注解后，编译后，其实是创建了 重载方法，所重载的方法的参数为Kotlin中定义时未指定
 * 默认的参数，这样，Java就可以 使用到Kotlin定义的默认值了
 */
class TestOverLoad @JvmOverloads constructor(x: Int,y: String = "hello"){
    //比如 本类的构造方法，在Java中使用的时候会存在 一个重载的构造方法: TestOverLoad obj = new TestOverLoad(1),这样Java就可以使用Kotlin默认指定的y="hello"的参数值了
}
fun main(args: Array<String>) {

}