package jvm.zhou_zhi_ming.c08.test3;

import java.io.Serializable;

/**
 * 当方法调用时直接传入字面量（字面量不需要定义，没有显示的静态类型）
 * 会在多个重载版本中找一个更加合适的版本，如传入1在int和long间回现在int
 */
public class Overload {
    public static void sayHello(Object arg){
        System.out.println("hello Object");
    }
    public static void sayHello(int arg){
        System.out.println("hello int");
    }
    public static void sayHello(long arg){
        System.out.println("hello long");
    }
    public static void sayHello(Character arg){
        System.out.println("hello Character");
    }
    public static void sayHello(char arg){
        System.out.println("hello char");
    }
    public static void sayHello(char ... arg){
        System.out.println("hello char……");
    }
    public static void sayHello(Serializable arg){
        System.out.println("hello Serializable");
    }

    public static void main(String[] args) {
        sayHello(1);
    }
}
