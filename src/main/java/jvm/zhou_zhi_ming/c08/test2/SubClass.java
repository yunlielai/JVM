package jvm.zhou_zhi_ming.c08.test2;

/**
 * 非虚方法演示
 */
public class SubClass extends SuperClass {
    public static void say1() {
    }
    private void say2() {
    }
    public static void say3() {
    }

    public final void say4(){

    }

    public static void main(String[] args) {
        //调用静态方法
        say1();
        //调用实例方法
        SubClass subClass=new SubClass();
        //调用私有方法
        subClass.say2();
        //调用父类方法
        say3();
        //调用final方法
        subClass.say4();
    }
}
