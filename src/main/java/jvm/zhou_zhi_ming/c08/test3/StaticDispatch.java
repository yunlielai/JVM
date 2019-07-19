package jvm.zhou_zhi_ming.c08.test3;

/**
 * 演示静态分派(方法重载)
 * 静态分派会在编译时就能确定方法的版本，并在编译时就将确定的版本作为写入到invokevirtual指令参数中
 */
public class StaticDispatch {
    static abstract class Human{
    }
    static class Man extends Human{
    }
    static class Woman extends Human{
    }
    public void sayHello(Human guy){
        System.out.println("hello,guy!");
    }
    public void sayHello(Man guy){
        System.out.println("hello,gentleman!");
    }
    public void sayHello(Woman guy){
        System.out.println("hello,lady!");
    }
    public static void main(String[]args){
        Human man=new Man();
        Human woman=new Woman();
        StaticDispatch sr=new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);
    }
}
