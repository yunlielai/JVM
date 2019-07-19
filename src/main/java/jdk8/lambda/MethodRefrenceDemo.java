package jdk8.lambda;

public class MethodRefrenceDemo {
    public static int staticMethod(int i) {
        return i*2;
    }

    public int normalMethod(MethodRefrenceDemo this,int i) {
        System.out.println("实例方法可以访问this:" + this);
        return i*3;
    }


}
