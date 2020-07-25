package org.flyfish.springstudy.kotlinstudy;

class JavaUseKotlin {
    public static void main(String[] args) {
        Test test = new Test();
        JavaAndKotlinKt.testA();//testA()方法为定义在 JavaAndKotlin.kt文件的 package 包路径下，即会成为JavaAndKotlin编译后
        //JavaAndKotlinKt.class 的静态方法,,并且 JavaAndKotlinKt.class 是无法在Java中使用 new关键字来创建该类的对象的
//        JavaAndKotlinKt javaAndKotlinKt = new JavaAndKotlinKt();//这里是不行的,因为在生成的字节码中没有不带参数的构造方法



    }
}
