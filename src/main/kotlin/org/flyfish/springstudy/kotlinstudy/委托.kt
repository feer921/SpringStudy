package org.flyfish.springstudy.kotlinstudy

/**
 * 委托：
 * Java的经典23种设计模式之委托模式
 * 分为 两种
 * 一：类委托
 */
interface IPrint{
    fun toPrint()

}
class PrinterImp(var name: String) : IPrint {
    override fun toPrint() {
        println("welcom $name")
    }
}

/**
 * 打印工作者，实现了 IPrint接口， 但是实现却委托给了传参数的实例
 * Kotlin委托实现原理：
 * by关键字后面的对象实际上会被存储在类的内部，编译器则会将父接口中的方法实现出来，并且将实现转移给委托对象去进行
 */
class PrinterWorker(printer: IPrint) : IPrint by printer{
    override fun toPrint() {//如果自己实现了，则使用自己的实现
        println("my self to print")
    }
}


fun main(args: Array<String>) {
    val myPrinter = PrinterImp("小米打印机")
    PrinterWorker(myPrinter).toPrint()


}
