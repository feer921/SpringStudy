package org.flyfish.springstudy.beans;

import com.sun.org.apache.xpath.internal.operations.String;

import java.util.ArrayList;
import java.util.List;

/**
 * PECS: producter extends(生产者 使用 继承,只读); consumer super(消费者使用 super 只写)
 * Java 的协变与逆变
 */
public class GeneritcListTest {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
//        animals = cats;//这样直接将 子类类型赋值给父类，报错，但实际上呢 子类又是父类的元素
        //上面为什么报错，反推，如果上面是编译通过的
        //那么就可以
        animals.add(new Dog());
        //而 animals = cats; 所以 但 cats又不是 Dog,所以就造成 元素类型不一致，
        //所以Java以 ? extends 父类 的方式，来将子类可以直接赋值给父类，这种即称为 协变
        List<? extends Animal> allAnimals = cats;//子类 直接赋值给 父类、即为协变
//        allAnimals.add(new Cat());//但是呢，又不能直接往里面添加 Cat类型的元素，
        //因为 allAnimals集合本身元素类型声明为 Animal和其子类，如果上面可以添加，则会导致集合内元素类型的混乱，即协变是只读的，不可写

        //-------------------> 逆变

        List<Animal> theAnimal = new ArrayList<>();

        //Animal类及 Animal父类类型
        List<? super Animal> contravariantAnimals = theAnimal; //

        //写：可以
        contravariantAnimals.add(new Cat()); //根据多态 Cat也是Animal
        contravariantAnimals.add(new Dog());
        //读：不可以，因为 contravariantAnimals内不一定就是Animal，因为可能是Animal的父类，所以逆变 只写不可读
//        Animal aAnimal = contravariantAnimals.get(0);
//        contravariantAnimals.get(0);

        //Java 的数组天然的支持 协变，但协变是有问题的，如：

        Object[] objs = new java.lang.String[]{
           "a",
           "b"
        };
        objs[0] = new Object();//虽然 编译不报错，但运行时报错，因为Objs实际为 字符串数组，导致存储异常

    }
}

class Animal{

}
class Dog extends Animal{

}
class Cat extends Animal{

}
