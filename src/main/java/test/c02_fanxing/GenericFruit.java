package test.c02_fanxing;

import java.util.ArrayList;
import java.util.List;

public class GenericFruit {
    static class Fruit{
        @Override
        public String toString() {
            return "fruit";
        }
    }

   static class Apple extends Fruit{
        @Override
        public String toString() {
            return "apple";
        }
    }

   static class Person{
        @Override
        public String toString() {
            return "Company";
        }
    }

   static class GenerateTest<T>{
        public void show_1(T t){
            System.out.println(t.toString());
        }

        //由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，编译器也能够正确识别泛型方法中识别的泛型。
        public <E> void show_3(E t){
            System.out.println(t.toString());
        }

        //在泛型类中声明了一个泛型方法，使用泛型T，注意这个T是一种全新的类型，可以与泛型类中声明的T不是同一种类型。
        public <T> void show_2(T t){
            System.out.println(t.toString());
        }

        //形参中不支持继承关系
       public void show_4(List<? super T> fruitList) {
           System.out.println(fruitList);
       }

       //编译报错，静态方法中不能使用类上定义的泛型,需在static关键字后定义方法中使用的泛型
       //public static void show_5(T t) {
       //}

    }

    public static void main(String[] args) {
        Apple apple = new Apple();
        Person person = new Person();

        GenerateTest<Fruit> generateTest = new GenerateTest<Fruit>();
        //apple是Fruit的子类，所以这里可以
        generateTest.show_1(apple);
        //编译器会报错，因为泛型类型实参指定的是Fruit，而传入的实参类是Person
        //generateTest.show_1(Company);

        //使用这两个方法都可以成功
        generateTest.show_2(apple);
        generateTest.show_2(person);

        //使用这两个方法也都可以成功
        generateTest.show_3(apple);
        generateTest.show_3(person);

        List<Fruit> fruitList=new ArrayList<>();
        List<Apple> appleList=new ArrayList<>();
        generateTest.show_4(fruitList);
        //编译报错，可使用通配符写成show_4中的参数改成(? extends T) 或 (<? super Apple> )
        //generateTest.show_4(appleList);

    }

}
